package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.dto.IgProfileDto;
import au.com.treeshake.phantombust.entity.IgProfile;
import au.com.treeshake.phantombust.model.ProcessingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Slf4j
@Service
public class IgProfileCsvProcessingService {

    private final ProcessingConfig<IgProfileDto, IgProfile> processing;

    public IgProfileCsvProcessingService(ProcessingConfig<IgProfileDto, IgProfile> processing) {
        this.processing = processing;
    }

    public void importFile() throws IOException {
        URL resource = Objects.requireNonNull(IgFollowingCsvProcessingService.class.getResource("/data/ig-profile/ig-profile-batch-1.csv"));
        File file = new File(resource.getFile());
        processing.getCsvProcessor().processFile(file, processing.getConverter(), processing.getRepository());
    }
}
