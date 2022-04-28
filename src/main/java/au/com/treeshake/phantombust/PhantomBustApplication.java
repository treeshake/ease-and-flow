package au.com.treeshake.phantombust;

import au.com.treeshake.phantombust.service.csv.IgProfileCsvProcessingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PhantomBustApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(PhantomBustApplication.class, args);
//        IgFollowingCsvProcessor bean = ctx.getBean(IgFollowingCsvProcessor.class);
//        bean.importFile();
//        IgUserCsvProcessorService bean = ctx.getBean(IgUserCsvProcessorService.class);
//        bean.importFile();
        IgProfileCsvProcessingService bean = ctx.getBean(IgProfileCsvProcessingService.class);
        bean.importFile();
    }
}
