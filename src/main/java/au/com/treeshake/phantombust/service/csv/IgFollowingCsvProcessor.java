package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.config.PhantomBustConfiguration;
import au.com.treeshake.phantombust.domain.DataEntry;
import au.com.treeshake.phantombust.dto.IgFollowingDto;
import au.com.treeshake.phantombust.repository.IgFollowingRepository;
import au.com.treeshake.phantombust.typeconverter.IgFollowingDtoToEntityConverter;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class IgFollowingCsvProcessor {

    private final PhantomBustConfiguration config;
    private final CsvMapper csvMapper;
    private final CsvSchema igFollowingCsvSchema;
    private final IgFollowingDtoToEntityConverter converter;
    private final IgFollowingRepository repository;

    public IgFollowingCsvProcessor(PhantomBustConfiguration config,
                                   CsvMapper csvMapper,
                                   CsvSchema igFollowingCsvSchema,
                                   IgFollowingDtoToEntityConverter converter,
                                   IgFollowingRepository repository) {
        this.config = config;
        this.csvMapper = csvMapper;
        this.igFollowingCsvSchema = igFollowingCsvSchema;
        this.converter = converter;
        this.repository = repository;
    }

    public void importCsvFile() throws IOException {
        String url = config.getImportUrls().getIgFollowing().getCsv();
        log.info("Fetching CSV file from URL: {}", url);

        File file = new File(Objects.requireNonNull(IgFollowingCsvProcessor.class.getResource("/result.csv")).getFile());
        LineIterator lineIterator = FileUtils.lineIterator(file, "UTF-8");
        List<DataEntry> dataEntries = new ArrayList<>();
        while (lineIterator.hasNext()) {
            DataEntry data = new DataEntry(dataEntries.size(), lineIterator.nextLine());
            try {
                repository.save(Objects.requireNonNull(converter.convert(mapLine(data.getRawData()))));
            } catch (Exception e) {
                log.warn("Could not process record at row={} data='{}'", data.getLineNumber(), data.getRawData(), e);
                data.setError(e);
            } finally {
                dataEntries.add(data);
            }
        }
        long countErrors = dataEntries.stream().filter(DataEntry::hasError).count();
        log.info("Finished. Processed {} rows. Encountered {} errors", dataEntries.size(), countErrors);
    }

    private IgFollowingDto mapLine(String line) throws IOException {
        MappingIterator<IgFollowingDto> mappingIterator = csvMapper
                .readerFor(IgFollowingDto.class)
                .with(igFollowingCsvSchema)
                .readValues(line);
        return mappingIterator.next();
    }
}
