package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.dto.IgUserDto;
import au.com.treeshake.phantombust.repository.IgUserRepository;
import au.com.treeshake.phantombust.typeconverter.IgUserDtoToEntityConverter;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        csvProcessor.processFile("/data/ig-user/user-list.csv", converter, repository);
    }
}
