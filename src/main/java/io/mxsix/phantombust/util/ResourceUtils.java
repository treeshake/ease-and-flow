package io.mxsix.phantombust.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.UrlResource;

/**
 * Utils class.
 */
public class ResourceUtils {

    long saveToFile(UrlResource urlResource, FileUrlResource out) throws IOException {
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(urlResource.getURL().openStream())) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(out.getFile())) {
                return fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            }
        }
    }
}
