package au.com.treeshake.phantombust.service.csv;

import au.com.treeshake.phantombust.model.RawData;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataEntryCsvService {

    private final List<RawData> dataEntries;
    private @Getter CsvSchema csvSchema;
    private @Getter boolean isHeader;

    public DataEntryCsvService() {
        dataEntries = new ArrayList<>();
    }

    public RawData setLine(String line) {
        if (CollectionUtils.isEmpty(dataEntries)) {
            this.csvSchema = inferSchema(line);
            this.isHeader = true;
        } else {
            this.isHeader = false;
        }
        int lineNumber = isHeader ? 1 : dataEntries.size();
        RawData rawData = new RawData(lineNumber, line);
        dataEntries.add(rawData);
        return rawData;
    }

    public void setError(Exception exception, String line) {
        RawData rawData = new RawData(dataEntries.size(), line, exception);
        dataEntries.add(rawData);
    }

    public int getLineNumber() {
        return dataEntries.size();
    }

    public long getErrorCount() {
        return dataEntries.stream().filter(RawData::hasError).count();
    }

    public long processed() {
        return dataEntries.size();
    }

    private static CsvSchema inferSchema(String line) {
        List<String> columns = Arrays.asList(StringUtils.split(line, ","));
        CsvSchema.Builder builder = CsvSchema.builder();
        columns.forEach(builder::addColumn);
        return builder.build();
    }

}
