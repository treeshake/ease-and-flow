package io.mxsix.phantombust;

import io.mxsix.phantombust.service.csv.IgProfileCsvProcessingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Main class.
 */
@SpringBootApplication
public class PhantomBustApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(PhantomBustApplication.class, args);
//        IgFollowingCsvProcessingService bean = ctx.getBean(IgFollowingCsvProcessingService.class);
//        bean.importFile();
//        IgUserCsvProcessorService bean = ctx.getBean(IgUserCsvProcessorService.class);
//        bean.importFile();
        IgProfileCsvProcessingService bean = ctx.getBean(IgProfileCsvProcessingService.class);
        bean.importFile();
    }
}
