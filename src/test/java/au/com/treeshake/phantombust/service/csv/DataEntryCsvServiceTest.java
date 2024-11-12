package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.model.CsvDataEntry;
import au.com.treeshake.phantombust.model.RawData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
@Disabled
public class DataEntryCsvServiceTest {

    private CsvDataEntry dataEntry;

    @BeforeEach
    public void beforeEach() {
        dataEntry = new CsvDataEntry();
    }

    @Test
    public void givensetEntry_whenHeader_thenProcessLineNumber() {
        dataEntry.setEntry("a,b,c");
        assertAll("Verify data entry service - set line",
                () -> assertThat(dataEntry.getLineNumber(), is(equalTo(1))),
                () -> assertThat(dataEntry.getCsvSchema(), is(notNullValue()))
        );
    }

    @Test
    public void givenErrorLine_whenSetError_thenProcessErrorLine() {
        dataEntry.setEntry("1,2,3"); // Set header first
        dataEntry.setEntry("d,e,f");
        dataEntry.getCurrentLine().markAsError(new RuntimeException());
        assertAll("Verify data entry service - set error",
                () -> assertThat(dataEntry.getStatistics().getErrorCount(), is(equalTo(1L))),
                () -> assertThat(dataEntry.getLineNumber(), is(equalTo(2))),
                () -> assertThat(dataEntry.getCsvSchema(), is(notNullValue()))
        );
    }

    @Test
    public void testRawDataEntries() {
        List<RawData> expected = Arrays.asList(new RawData(1, "Line 1"),
                new RawData(2, "Line 2"),
                new RawData(3, "Line 3")
        );
        dataEntry.setEntry("Line 1");
        dataEntry.setEntry("Line 2");
        dataEntry.setEntry("Line 3");

        List<RawData> actual = dataEntry.getDataEntries();
        assertIterableEquals(actual, expected);
    }

    @Test
    public void testThrows() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Some message");
        });
        assertThat(thrown.getMessage(), is(equalTo("Some message")));
        assertSame("Some message", thrown.getMessage());

    }
}