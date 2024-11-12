package io.mxsix.phantombust.model;

import java.util.Objects;
import lombok.Data;
import lombok.Getter;

/**
 * Model class.
 */
@Data
public class RawData {

    private final @Getter int lineNumber;
    private final @Getter String data;
    private Exception error;
    private boolean skipped;

    public RawData(int lineNumber, String data) {
        this.lineNumber = lineNumber;
        this.data = data;
    }

    public boolean hasError() {
        return error != null;
    }

    public boolean hasSkipped() {
        return skipped;
    }

    public void markAsSkipped() {
        skipped = true;
    }

    public void markAsError(Exception error) {
        this.error = error;
        this.skipped = true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RawData theOther)) {
            return false;
        }
        return Objects.equals(theOther.getLineNumber(), this.lineNumber)
            && Objects.equals(theOther.getData(), this.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.lineNumber, this.data);
    }
}
