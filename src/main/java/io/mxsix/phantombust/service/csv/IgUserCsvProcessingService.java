package io.mxsix.phantombust.service.csv;

import io.mxsix.phantombust.dto.IgUserDto;
import io.mxsix.phantombust.repository.IgUserRepository;
import io.mxsix.phantombust.typeconverter.IgUserDtoToEntityConverter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * Service class.
 */
@Service
public record IgUserCsvProcessingService(IgUserRepository repository,
                                         IgUserDtoToEntityConverter converter,
                                         CsvProcessor<IgUserDto> csvProcessor) {

    public void importFile() throws IOException {
        URL resource = Objects.requireNonNull(IgFollowingCsvProcessingService.class.getResource("/data/ig-user/user-list.csv"));
        File file = new File(resource.getFile());
        csvProcessor.processFile(file, converter, repository);
    }
}
