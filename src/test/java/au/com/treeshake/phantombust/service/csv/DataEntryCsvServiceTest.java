package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.model.RawData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class DataEntryCsvServiceTest {

    private DataEntryCsvService dataEntryCsvService;

    @BeforeEach
    public void beforeEach() {
        dataEntryCsvService = new DataEntryCsvService();
    }

    @Test
    public void givenSetLine_whenHeader_thenProcessLineNumber() {
        dataEntryCsvService.setLine("a,b,c");
        assertAll("Verify data entry service - set line",
                () -> assertThat(dataEntryCsvService.getLineNumber(), is(equalTo(1))),
                () -> assertThat(dataEntryCsvService.getCsvSchema(), is(notNullValue()))
        );
    }

    @Test
    public void givenErrorLine_whenSetError_thenProcessErrorLine() {
        dataEntryCsvService.setLine("a,b,c"); // Set header first
        dataEntryCsvService.setError(new RuntimeException(), "d,e,f");
        assertAll("Verify data entry service - set error",
                () -> assertThat(dataEntryCsvService.getErrorCount(), is(equalTo(1L))),
                () -> assertThat(dataEntryCsvService.getLineNumber(), is(equalTo(2))),
                () -> assertThat(dataEntryCsvService.getCsvSchema(), is(notNullValue()))
        );
    }

    @Test
    public void testRawDataEntries() {
        List<RawData> expected = Arrays.asList(new RawData(1, "Line 1"),
                new RawData(2, "Line 2"),
                new RawData(3, "Line 3")
        );
        dataEntryCsvService.setLine("Line 1");
        dataEntryCsvService.setLine("Line 2");
        dataEntryCsvService.setLine("Line 3");

        List<RawData> actual = dataEntryCsvService.getDataEntries();
        assertIterableEquals(actual, expected);
    }

    @Test
    public void testThrows() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Some message");
        });
        assertThat(thrown.getMessage(), is(equalTo("Some message")));
        assertSame(thrown.getMessage(), "Some message");

    }
}