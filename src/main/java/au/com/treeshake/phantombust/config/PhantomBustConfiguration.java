package au.com.treeshake.phantombust.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "phantombust")
public class PhantomBustConfiguration {

    private ImportUrls importUrls;

    @Data
    public static class ImportUrls {

        private IgFollowing igFollowing;

        @Data
        public static class IgFollowing {
            private String csv;
            private String json;
        }
    }
}
