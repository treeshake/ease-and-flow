package io.mxsix.phantombust.typeconverter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Converter class.
 */
@Component
public record StringToResourceConverter(ResourceLoader loader) implements Converter<String, Resource> {

    @Override
    public Resource convert(String location) {
        return loader.getResource(location);
    }
}
