package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.model.RawData;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Slf4j
public class CsvProcessor<D> {

    private final DataEntryCsvService dataEntry;
    private final Class<D> incomingType;
    private final CsvMapper csvMapper;

    public CsvProcessor(DataEntryCsvService dataEntry, CsvMapper csvMapper, Class<D> incomingType) {
        this.dataEntry = dataEntry;
        this.csvMapper = csvMapper;
        this.incomingType = incomingType;
    }

    public <E> void processFile(String classResource, Converter<D,E> converter, JpaRepository<E, Long> repository) throws IOException {
        URL resource = Objects.requireNonNull(IgFollowingCsvProcessingService.class.getResource(classResource));
        File file = new File(resource.getFile());
        LineIterator lineIterator = FileUtils.lineIterator(file, "UTF-8");
        while (lineIterator.hasNext()) {
            String line = "";
            try {
                line = lineIterator.nextLine();
                RawData raw = dataEntry.setLine(line);
                if (!dataEntry.isHeader()) {
                    D mapLine = mapLine(raw.getData(), dataEntry.getCsvSchema());
                    repository.save(Objects.requireNonNull(converter.convert(mapLine)));
                }
                log.info("Processed row {}", dataEntry.getLineNumber());
            } catch (DataIntegrityViolationException e) {
                log.warn("Could not process record at row={} data='{}' error={}", dataEntry.getLineNumber(), line, e.getCause().getMessage());
                dataEntry.setError(e, line);
            } catch (RuntimeException e) {
                log.warn("Could not process record at row={} data='{}'", dataEntry.getLineNumber(), line, e);
                dataEntry.setError(e, line);
            }
        }
        log.info("Finished. Processed {} rows. Encountered {} errors", dataEntry.processed(), dataEntry.getErrorCount());
    }

    private D mapLine(String line, CsvSchema schema) throws IOException {
        MappingIterator<D> mappingIterator = csvMapper
                .readerFor(incomingType)
                .with(schema)
                .readValues(line);
        return mappingIterator.next();
    }
}
