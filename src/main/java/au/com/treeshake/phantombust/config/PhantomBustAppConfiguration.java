package au.com.treeshake.phantombust.config;

import au.com.treeshake.phantombust.entity.IgProfile;
import au.com.treeshake.phantombust.repository.IgProfileRepository;
import au.com.treeshake.phantombust.service.csv.CsvProcessor;
import au.com.treeshake.phantombust.typeconverter.IgProfileDtoToEntityConverter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import au.com.treeshake.phantombust.dto.IgFollowingDto;
import au.com.treeshake.phantombust.dto.IgProfileDto;
import au.com.treeshake.phantombust.dto.IgUserDto;
import au.com.treeshake.phantombust.model.CsvDataEntry;
import au.com.treeshake.phantombust.model.ProcessingConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * PhantomBustAppConfiguration config class.
 */
@Configuration
public class PhantomBustAppConfiguration {

    @Bean
    public CsvMapper csvMapper() {
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.registerModule(new JavaTimeModule());
        csvMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return csvMapper;
    }

    @Bean
    public JsonMapper objectMapper() {
        JsonMapper jsonMapper = new JsonMapper();
        // Register defaults e.g. DateTime Java 8
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return jsonMapper;
    }

    @Bean
    @Scope("prototype")
    public CsvDataEntry dataEntryCsvService() {
        return new CsvDataEntry();
    }

    @Bean
    public CsvProcessor<IgUserDto> igUserCsvProcessor(CsvDataEntry dataEntry, CsvMapper csvMapper) {
        return new CsvProcessor<>(dataEntry, csvMapper, IgUserDto.class);
    }

    @Bean
    public CsvProcessor<IgFollowingDto> igFollowingCsvProcessor(CsvDataEntry dataEntry, CsvMapper csvMapper) {
        return new CsvProcessor<>(dataEntry, csvMapper, IgFollowingDto.class);
    }

    @Bean
    public ProcessingConfig<IgProfileDto, IgProfile> igProfileCsvProcessing(
            IgProfileRepository repository, IgProfileDtoToEntityConverter converter, CsvDataEntry dataEntry, CsvMapper csvMapper) {
        return new ProcessingConfig<>(repository, converter, new CsvProcessor<>(dataEntry, csvMapper, IgProfileDto.class));
    }
}
