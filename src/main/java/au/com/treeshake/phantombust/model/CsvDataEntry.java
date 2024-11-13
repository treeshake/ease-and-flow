package au.com.treeshake.phantombust.model;

import au.com.treeshake.phantombust.exception.DataEntryIsEmptyException;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Model class.
 */
public class CsvDataEntry {

    private static final String SEPARATOR_CHARACTER = ",";
    private static final char QUOTE_CHARACTER = '"';

    private final @Getter DataEntryStatistics statistics;
    private final @Getter List<RawData> dataEntries;
    private @Getter CsvSchema csvSchema;
    private @Getter boolean isHeader;
    private @Getter int columnCount;

    public CsvDataEntry() {
        dataEntries = new ArrayList<>();
        statistics = new DataEntryStatistics(dataEntries);
    }

    public RawData setEntry(String line) {
        int lineNumber;
        if (CollectionUtils.isEmpty(dataEntries)) {
            List<String> header = splitData(line);
            columnCount = header.size();
            csvSchema = inferSchema(header);
            isHeader = true;
            lineNumber = 1;
        } else {
            lineNumber = dataEntries.size() - 1;
            isHeader = false;
        }
        RawData rawData = new RawData(lineNumber, line);
        dataEntries.add(rawData);
        return rawData;
    }

    public RawData getCurrentLine() {
        if (CollectionUtils.isEmpty(dataEntries)) {
            throw new DataEntryIsEmptyException("No data has been entered. Add at least one line must be set before calling getCurrentLine()");
        }
        if (isHeader) {
            return dataEntries.get(0);
        }
        return dataEntries.get(dataEntries.size() - 1);
    }

    public int getLineNumber() {
        return dataEntries.size();
    }

    private static List<String> splitData(String line) {
        return Arrays.asList(StringUtils.split(line, SEPARATOR_CHARACTER));
    }

    private static CsvSchema inferSchema(List<String> data) {
        CsvSchema.Builder builder = CsvSchema.builder();
//            .setQuoteChar('"'); // Format this in Excel, which removes quote char
        data.forEach(builder::addColumn);
        return builder.build();
    }

}
