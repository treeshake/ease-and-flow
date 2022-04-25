package au.com.treeshake.phantombust.domain;

import lombok.Data;

@Data
public class DataEntry {

    private final int lineNumber;
    private final String rawData;
    private Exception error;

    public DataEntry(int lineNumber, String rawData) {
        this.lineNumber = lineNumber;
        this.rawData = rawData;
    }

    public boolean hasError() {
        return error != null;
    }
}
