package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.dto.IgProfileDto;
import au.com.treeshake.phantombust.entity.IgProfile;
import au.com.treeshake.phantombust.model.ProcessingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Slf4j
@Service
public class IgProfileCsvProcessingService {

    private final ProcessingConfig<IgProfileDto, IgProfile> processing;
    private final ResourceLoader resourceLoader;

    public IgProfileCsvProcessingService(ProcessingConfig<IgProfileDto, IgProfile> processing, ResourceLoader resourceLoader) {
        this.processing = processing;
        this.resourceLoader = resourceLoader;
    }

    public void importFile() throws IOException {
        URL resource = Objects.requireNonNull(resourceLoader.getResource("file:/data/instagram-profile-scraper-2022.csv")).getURL();
        File file = new File(resource.getFile());
        processing.getCsvProcessor().processFile(file, processing.getConverter(), processing.getRepository());
    }
}
