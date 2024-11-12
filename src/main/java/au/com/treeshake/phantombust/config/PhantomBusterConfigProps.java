package au.com.treeshake.phantombust.config;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

/**
 * PhantomBusterConfigProps config class.
 */
@Validated
@Data
@Configuration
@ConfigurationProperties(prefix = "app.phantombuster")
public class PhantomBusterConfigProps {

    @NotNull
    private DataImport dataImport;

    @Validated
    @Data
    public static class DataImport {

        @NotEmpty
        private List<Resource> followingCollectors;
    }
}
