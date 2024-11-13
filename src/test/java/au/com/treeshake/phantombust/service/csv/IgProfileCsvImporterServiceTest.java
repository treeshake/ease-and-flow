package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.config.PhantomBusterConfigProps;
import au.com.treeshake.phantombust.dto.IgProfileDto;
import au.com.treeshake.phantombust.entity.IgProfile;
import au.com.treeshake.phantombust.model.CsvDataEntry;
import au.com.treeshake.phantombust.model.ProcessingConfig;
import au.com.treeshake.phantombust.repository.IgProfileRepository;
import au.com.treeshake.phantombust.service.csv.CsvProcessor;
import au.com.treeshake.phantombust.service.csv.IgProfileCsvProcessingService;
import au.com.treeshake.phantombust.typeconverter.IgProfileDtoToEntityConverter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@DataJpaTest
@Disabled
public class IgProfileCsvImporterServiceTest {

    @Autowired
    private IgProfileCsvProcessingService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testRun() throws IOException {
        service.importFile();
    }

    @Import(PhantomBusterConfigProps.class)
    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public CsvMapper csvMapper() {
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.registerModule(new JavaTimeModule());
            csvMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            return csvMapper;
        }

        @Bean
        @Scope("prototype")
        public CsvDataEntry dataEntryCsvService() {
            return new CsvDataEntry();
        }

        @Bean
        public ProcessingConfig<IgProfileDto, IgProfile> igProfileCsvProcessing(IgProfileRepository repository, IgProfileDtoToEntityConverter converter, CsvDataEntry dataEntry, CsvMapper csvMapper) {
            return new ProcessingConfig<>(repository, converter, new CsvProcessor<>(dataEntry, csvMapper, IgProfileDto.class));
        }

        @Bean
        public IgProfileDtoToEntityConverter converter(IgProfileRepository repository) {
            return new IgProfileDtoToEntityConverter(repository);
        }

        @Bean
        public IgProfileCsvProcessingService IgProfileImportService(PhantomBusterConfigProps configProps, ProcessingConfig<IgProfileDto, IgProfile> processing) {
            return new IgProfileCsvProcessingService(configProps, processing);
        }
    }
}