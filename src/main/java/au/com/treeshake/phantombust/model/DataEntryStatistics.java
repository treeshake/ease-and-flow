package au.com.treeshake.phantombust.model;

import java.util.List;

/**
 * Record class.
 */
public record DataEntryStatistics(List<RawData> dataEntries) {

    public long getErrorCount() {
        return dataEntries.stream().filter(RawData::hasError).count();
    }

    public double getErrorPercentage() {
        return ((double) getErrorCount() / getTotalProcessed()) * 100;
    }

    public long getSkippedCount() {
        return dataEntries.stream().filter(RawData::hasSkipped).count();
    }

    public double getSkippedPercentage() {
        return ((double) getSkippedCount() / getTotalProcessed()) * 100;
    }

    public long getSuccessCount() {
        return getTotalProcessed() - getSkippedCount() - getErrorCount();
    }

    public double getSuccessPercentage() {
        return ((double) getSuccessCount() / getTotalProcessed()) * 100;
    }

    public long getTotalProcessed() {
        return dataEntries.size();
    }

}
