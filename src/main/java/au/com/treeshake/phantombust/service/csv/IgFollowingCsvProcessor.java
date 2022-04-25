package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.config.PhantomBustConfiguration;
import au.com.treeshake.phantombust.dto.IgFollowingDto;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Slf4j
@Service
public class IgFollowingCsvProcessor {

    private final PhantomBustConfiguration config;
    private final CsvMapper csvMapper;

    public IgFollowingCsvProcessor(PhantomBustConfiguration config, CsvMapper csvMapper) {
        this.config = config;
        this.csvMapper = csvMapper;
    }

    public void importCsvFile() throws IOException {
        String url = config.getImportUrls().getIgFollowing().getCsv();
        log.info("Downloading CSV file from URL: {}", url);

        log.info("Configuring CSV Schema");
        CsvSchema schema = CsvSchema.builder()
                .addColumn("query")
                .addColumn("timestamp")
                .addColumn("error")
                .addColumn("profileUrl")
                .addColumn("username")
                .addColumn("fullName")
                .addColumn("imgUrl")
                .addColumn("id", CsvSchema.ColumnType.NUMBER)
                .addColumn("isPrivate", CsvSchema.ColumnType.BOOLEAN)
                .addColumn("isVerified", CsvSchema.ColumnType.BOOLEAN)
                .build();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            int i = 1;
            while (reader.ready()) {
                MappingIterator<IgFollowingDto> mappingIterator = csvMapper
                        .readerFor(IgFollowingDto.class)
                        .with(schema)
                        .readValues(reader.readLine());
                IgFollowingDto value = mappingIterator.next();
                log.info("Mapped record: {} as {}", i, value);
                i++;
            }
        }
    }
}
