package au.com.treeshake.phantombust.util;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

/**
 * Utils class.
 */
@UtilityClass
public final class DateUtils {

    public static final DateTimeFormatter ISO_INSTANT_MILLISECOND = DateTimeFormatter
        .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        .withZone(ZoneId.of("UTC"));
}
