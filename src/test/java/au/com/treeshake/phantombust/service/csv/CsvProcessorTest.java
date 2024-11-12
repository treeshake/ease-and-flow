package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.dto.IgFollowingDto;
import au.com.treeshake.phantombust.repository.IgProfileRepository;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
//@TestPropertySource(locations = "classpath:application-test.yml")
//@DataJpaTest
//@Sql(scripts = "classpath:sql/create_ig_following.sql")
class CsvProcessorTest {

    @MockBean
    private IgProfileRepository igProfileRepository;

    @Autowired
    private CsvProcessor<IgFollowingDto> csvProcessor;

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public CsvProcessor<IgFollowingDto> igFollowingCsvProcessor(DataEntryCsvService dataEntry, CsvMapper csvMapper) {
            return new CsvProcessor<>(dataEntry, csvMapper, IgFollowingDto.class);
        }

        @Bean
        @Scope("prototype")
        public DataEntryCsvService dataEntryCsvService() {
            return new DataEntryCsvService();
        }

        @Bean
        public CsvMapper csvMapper() {
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.registerModule(new JavaTimeModule());
            csvMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            return csvMapper;
        }
    }

    @BeforeEach
    public void setUp() {

    }


    @Test
    public void givenProcessFile_whenNotHeader_thenSaveRecord() {
        assertTrue(true);
    }
}