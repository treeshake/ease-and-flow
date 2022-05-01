package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.dto.IgFollowingDto;
import au.com.treeshake.phantombust.repository.IgFollowingRepository;
import au.com.treeshake.phantombust.typeconverter.IgFollowingDtoToEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

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
        URL resource = Objects.requireNonNull(IgFollowingCsvProcessingService.class.getResource("/data/ig-following/processed/batch-1-2020-04-28.csv"));
        File file = new File(resource.getFile());
        csvProcessor.processFile(file, converter, repository);
    }

}
