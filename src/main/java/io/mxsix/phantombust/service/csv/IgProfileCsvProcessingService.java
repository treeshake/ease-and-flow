package io.mxsix.phantombust.service.csv;

import io.mxsix.phantombust.config.PhantomBusterConfigProps;
import io.mxsix.phantombust.dto.IgProfileDto;
import io.mxsix.phantombust.entity.IgProfile;
import io.mxsix.phantombust.model.ProcessingConfig;
import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 * Service class.
 */
@Service
public record IgProfileCsvProcessingService(PhantomBusterConfigProps configProps,
                                            ProcessingConfig<IgProfileDto, IgProfile> processing) {

    public void importFile() throws IOException {
        File file = ResourceUtils.getFile("file:/phantombust-data/ig-profile/ig-profile-single-entry.csv");
//        URL resource = Objects.requireNonNull(IgFollowingCsvProcessingService.class.getResource("/data/ig-profile/ig-profile-batch-1.csv"));
//        File file = new File(resource.getFile());
        processing.csvProcessor().processFile(file, processing.converter(), processing.repository());
//        @NotEmpty List<Resource> resources = configProps.getDataImport().getFollowingCollectors();
    }

}