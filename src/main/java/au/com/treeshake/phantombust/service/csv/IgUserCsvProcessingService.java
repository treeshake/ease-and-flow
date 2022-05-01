package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.dto.IgUserDto;
import au.com.treeshake.phantombust.repository.IgUserRepository;
import au.com.treeshake.phantombust.typeconverter.IgUserDtoToEntityConverter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Service
public class IgUserCsvProcessingService {

    private final IgUserRepository repository;
    private final IgUserDtoToEntityConverter converter;
    private final CsvProcessor<IgUserDto> csvProcessor;

    public IgUserCsvProcessingService(IgUserRepository repository, IgUserDtoToEntityConverter converter, CsvProcessor<IgUserDto> csvProcessor) {
        this.repository = repository;
        this.converter = converter;
        this.csvProcessor = csvProcessor;
    }

    public void importFile() throws IOException {
        URL resource = Objects.requireNonNull(IgFollowingCsvProcessingService.class.getResource("/data/ig-user/user-list.csv"));
        File file = new File(resource.getFile());
        csvProcessor.processFile(file, converter, repository);
    }
}
