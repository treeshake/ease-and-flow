package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.repository.IgFollowingRepository;
import au.com.treeshake.phantombust.typeconverter.IgFollowingDtoToEntityConverter;
import au.com.treeshake.phantombust.dto.IgFollowingDto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 * Service class.
 */
@Slf4j
@Service
public record IgFollowingCsvProcessingService(IgFollowingRepository repository,
                                              IgFollowingDtoToEntityConverter converter,
                                              CsvProcessor<IgFollowingDto> csvProcessor) {

    public void importFile() throws IOException {
        ResourceUtils.getFile("");
        URL resource = Objects.requireNonNull(IgFollowingCsvProcessingService.class
            .getResource("/data/ig-following/processed/batch-1-2020-04-28.csv"));
        File file = new File(resource.getFile());
        csvProcessor.processFile(file, converter, repository);
    }

}
