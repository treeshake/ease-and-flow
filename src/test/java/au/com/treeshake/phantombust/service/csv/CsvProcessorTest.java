package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.dto.IgProfileDto;
import au.com.treeshake.phantombust.repository.IgProfileRepository;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.ResourceUtils;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CsvProcessorTest {

    @MockBean
    private IgProfileRepository igProfileRepository;

    @InjectMocks
    private CsvProcessor<IgProfileDto> csvProcessor;

    @Test
    public void givenFileWithNewLine_expectFileSanitisedCorrectly() throws IOException {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "csv/ig-profile-single-entry.csv");

        File processedFile = csvProcessor.sanitiseFile(file);

        LineIterator lineIterator = FileUtils.lineIterator(processedFile);

        int noOfLines = 0;
        while (lineIterator.hasNext()) {
            lineIterator.nextLine();
            noOfLines++;
        }

        assertThat(noOfLines).isEqualTo(3);
    }

    @Test
    public void givenFileWithExtraCommaCharacters_expectFileSanitisedCorrectly() throws IOException {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "csv/ig-profile-extra-commas.csv");

        File processedFile = csvProcessor.sanitiseFile(file);

        LineIterator lineIterator = FileUtils.lineIterator(processedFile);

        int noOfLines = 0;
        while (lineIterator.hasNext()) {
            lineIterator.nextLine();
            noOfLines++;
        }

        assertThat(noOfLines).isEqualTo(3);
    }
}