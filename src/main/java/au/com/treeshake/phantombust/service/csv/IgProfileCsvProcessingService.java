package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.dto.IgProfileDto;
import au.com.treeshake.phantombust.entity.IgProfile;
import au.com.treeshake.phantombust.model.ProcessingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class IgProfileCsvProcessingService {

    private final ProcessingConfig<IgProfileDto, IgProfile> processing;

    public IgProfileCsvProcessingService(ProcessingConfig<IgProfileDto, IgProfile> processing) {
        this.processing = processing;
    }

    public void importFile() throws IOException {
        processing.getCsvProcessor()
                .processFile("/data/ig-profile/ig-profile-batch-1.csv", processing.getConverter(), processing.getRepository());
    }
}
