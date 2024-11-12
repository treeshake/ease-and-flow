package au.com.treeshake.phantombust.typeconverter;

import au.com.treeshake.phantombust.repository.IgUserRepository;
import au.com.treeshake.phantombust.dto.IgUserDto;
import au.com.treeshake.phantombust.entity.IgUser;

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
