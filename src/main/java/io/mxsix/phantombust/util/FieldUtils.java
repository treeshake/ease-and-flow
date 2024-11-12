package io.mxsix.phantombust.util;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Utils class.
 */
@UtilityClass
public class FieldUtils {

    public static ZonedDateTime parseNullableTimestamp(String timestamp) {
        return parseOptionalTimestamp(timestamp).orElse(null);
    }

    public static Optional<ZonedDateTime> parseOptionalTimestamp(String timestamp) {
        try {
            if (StringUtils.isNotBlank(timestamp)) {
                return Optional.of(ZonedDateTime.parse(timestamp, DateUtils.ISO_INSTANT_MILLISECOND));
            }
        } catch (DateTimeParseException e) {
            // Leave as empty
        }
        return Optional.empty();
    }

    public static BigInteger parseInstagramId(String id) {
        try {
            if (StringUtils.isNotBlank(id)) {
                return new BigInteger(id);
            }
        } catch (NumberFormatException e) {
            // Default to zero
        }
        return BigInteger.ZERO;
    }

    public static Integer parseNullableInteger(String value) {
        try {
            if (StringUtils.isNotBlank(value)) {
                return NumberUtils.createInteger(value);
            }
        } catch (NumberFormatException e) {
            // Ignore, return null
        }
        return null;
    }
}
