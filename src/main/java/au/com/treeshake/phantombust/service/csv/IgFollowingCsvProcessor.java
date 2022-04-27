package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.config.PhantomBustConfiguration;
import au.com.treeshake.phantombust.dto.IgFollowingDto;
import au.com.treeshake.phantombust.model.RawData;
import au.com.treeshake.phantombust.repository.IgFollowingRepository;
import au.com.treeshake.phantombust.typeconverter.IgFollowingDtoToEntityConverter;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class IgFollowingCsvProcessor {

    private final PhantomBustConfiguration config;
    private final IgFollowingDtoToEntityConverter converter;
    private final DataEntryCsvService dataEntry;
    private final IgFollowingRepository repository;
    private final CsvMapper csvMapper;

    public IgFollowingCsvProcessor(PhantomBustConfiguration config,
                                   CsvMapper csvMapper,
                                   IgFollowingDtoToEntityConverter converter,
                                   DataEntryCsvService dataEntry,
                                   IgFollowingRepository repository) {
        this.config = config;
        this.csvMapper = csvMapper;
        this.converter = converter;
        this.dataEntry = dataEntry;
        this.repository = repository;
    }

    public void importCsvFile() throws IOException {
        String url = config.getImportUrls().getIgFollowing().getCsv();
        log.info("Fetching CSV file from URL: {}", url);

        File file = new File(Objects.requireNonNull(IgFollowingCsvProcessor.class.getResource("/result-5.csv")).getFile());
        LineIterator lineIterator = FileUtils.lineIterator(file, "UTF-8");
        while (lineIterator.hasNext()) {
            String line = "";
            try {
                line = lineIterator.nextLine();
                RawData raw = dataEntry.setLine(line);
                if (!dataEntry.isHeader()) {
                    repository.save(Objects.requireNonNull(converter.convert(mapLine(raw.getData()))));
                }
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

    private IgFollowingDto mapLine(String line) throws IOException {
        MappingIterator<IgFollowingDto> mappingIterator = csvMapper
                .readerFor(IgFollowingDto.class)
                .with(dataEntry.getCsvSchema())
                .readValues(line);
        return mappingIterator.next();
    }

}
