package au.com.treeshake.phantombust.model;

import lombok.Data;

@Data
public class RawData {

    private final int lineNumber;
    private final String data;
    private Exception error;

    public RawData(int lineNumber, String data) {
        this.lineNumber = lineNumber;
        this.data = data;
    }

    public RawData(int lineNumber, String data, Exception error) {
        this.lineNumber = lineNumber;
        this.data = data;
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }
}
