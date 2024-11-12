package io.mxsix.phantombust.typeconverter;

import io.mxsix.phantombust.dto.IgUserDto;
import io.mxsix.phantombust.entity.IgUser;
import io.mxsix.phantombust.repository.IgUserRepository;
import javax.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter class.
 */
@Component
public record IgUserDtoToEntityConverter(IgUserRepository repository) implements Converter<IgUserDto, IgUser> {

    @Override
    public IgUser convert(@NotNull IgUserDto source) {
        IgUser igUser = repository.findOneByUsername(source.getUsername()).orElse(new IgUser());
        igUser.setUsername(source.getUsername());
        return igUser;
    }
}
