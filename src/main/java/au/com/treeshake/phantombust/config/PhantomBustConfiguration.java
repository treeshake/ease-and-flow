package au.com.treeshake.phantombust.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Data
@Configuration
@ConfigurationProperties(prefix = "phantombust")
public class PhantomBustConfiguration {

    @NotNull
    private ImportUrls importUrls;

    @Validated
    @Data
    public static class ImportUrls {

        @NotNull
        private IgFollowing igFollowing;

        @Data
        public static class IgFollowing {
            private String csv;
            private String json;
        }
    }
}
