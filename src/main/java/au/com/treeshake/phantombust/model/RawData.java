package au.com.treeshake.phantombust.model;

import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RawData theOther)) {
            return false;
        }
        return Objects.equals(theOther.getLineNumber(), this.lineNumber) &&
               Objects.equals(theOther.getData(), this.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.lineNumber, this.data);
    }
}
