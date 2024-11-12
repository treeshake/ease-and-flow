package io.mxsix.phantombust.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.mxsix.phantombust.dto.IgFollowingDto;
import io.mxsix.phantombust.dto.IgProfileDto;
import io.mxsix.phantombust.dto.IgUserDto;
import io.mxsix.phantombust.entity.IgProfile;
import io.mxsix.phantombust.model.CsvDataEntry;
import io.mxsix.phantombust.model.ProcessingConfig;
import io.mxsix.phantombust.repository.IgProfileRepository;
import io.mxsix.phantombust.service.csv.CsvProcessor;
import io.mxsix.phantombust.typeconverter.IgProfileDtoToEntityConverter;
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
