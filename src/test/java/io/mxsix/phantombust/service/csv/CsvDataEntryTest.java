package io.mxsix.phantombust.service.csv;

import io.mxsix.phantombust.model.CsvDataEntry;
import io.mxsix.phantombust.model.RawData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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

@ExtendWith(MockitoExtension.class)
@Disabled
public class CsvDataEntryTest {

    private CsvDataEntry csvDataEntry;

    @BeforeEach
    public void beforeEach() {
        csvDataEntry = new CsvDataEntry();
    }

    @Test
    public void givenSetLine_whenHeader_thenProcessLineNumber() {
        csvDataEntry.setEntry("a,b,c");
        assertAll("Verify data entry service - set line",
                () -> assertThat(csvDataEntry.getLineNumber(), is(equalTo(1))),
                () -> assertThat(csvDataEntry.getCsvSchema(), is(notNullValue()))
        );
    }

    @Test
    public void givenErrorLine_whenSetError_thenProcessErrorLine() {
        csvDataEntry.setEntry("1,2,3"); // Set header first
        csvDataEntry.setEntry( "d,e,f");
        csvDataEntry.getCurrentLine().markAsError(new RuntimeException());
        assertAll("Verify data entry service - set error",
                () -> assertThat(csvDataEntry.getStatistics().getErrorCount(), is(equalTo(1L))),
                () -> assertThat(csvDataEntry.getLineNumber(), is(equalTo(2))),
                () -> assertThat(csvDataEntry.getCsvSchema(), is(notNullValue()))
        );
    }

    @Test
    public void testRawDataEntries() {
        List<RawData> expected = Arrays.asList(new RawData(1, "Line 1"),
                new RawData(2, "Line 2"),
                new RawData(3, "Line 3")
        );
        csvDataEntry.setEntry("Line 1");
        csvDataEntry.setEntry("Line 2");
        csvDataEntry.setEntry("Line 3");

        List<RawData> actual = csvDataEntry.getDataEntries();
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