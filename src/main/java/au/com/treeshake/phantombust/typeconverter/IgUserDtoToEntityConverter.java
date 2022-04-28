package au.com.treeshake.phantombust.typeconverter;

import au.com.treeshake.phantombust.dto.IgUserDto;
import au.com.treeshake.phantombust.entity.IgUser;
import au.com.treeshake.phantombust.repository.IgUserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class IgUserDtoToEntityConverter implements Converter<IgUserDto, IgUser> {

    private final IgUserRepository repository;

    public IgUserDtoToEntityConverter(IgUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public IgUser convert(@NotNull IgUserDto source) {
        IgUser igUser = repository.findOneByUsername(source.getUsername()).orElse(new IgUser());
        igUser.setUsername(source.getUsername());
        return igUser;
    }
}
