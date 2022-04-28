package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.dto.IgFollowingDto;
import au.com.treeshake.phantombust.repository.IgFollowingRepository;
import au.com.treeshake.phantombust.typeconverter.IgFollowingDtoToEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class IgFollowingCsvProcessingService {

    private final IgFollowingRepository repository;
    private final IgFollowingDtoToEntityConverter converter;
    private final CsvProcessor<IgFollowingDto> csvProcessor;

    public IgFollowingCsvProcessingService(IgFollowingRepository repository, IgFollowingDtoToEntityConverter converter, CsvProcessor<IgFollowingDto> csvProcessor) {
        this.repository = repository;
        this.converter = converter;
        this.csvProcessor = csvProcessor;
    }

    public void importFile() throws IOException {
        csvProcessor.processFile("/data/ig-following/processed/batch-1-2020-04-28.csv", converter, repository);
    }

}
