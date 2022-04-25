package au.com.treeshake.phantombust.typeconverter;

import au.com.treeshake.phantombust.dto.IgFollowingDto;
import au.com.treeshake.phantombust.entity.IgFollowing;
import au.com.treeshake.phantombust.repository.IgFollowingRepository;
import au.com.treeshake.phantombust.util.DateUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Component
public class IgFollowingDtoToEntityConverter implements Converter<IgFollowingDto, IgFollowing> {

    private final IgFollowingRepository repository;

    public IgFollowingDtoToEntityConverter(IgFollowingRepository repository) {
        this.repository = repository;
    }

    @Override
    public IgFollowing convert(@NotNull IgFollowingDto source) {
        IgFollowing igFollowing = repository.findOneByQueryAndUsername(source.getQuery(), source.getUsername()).orElse(new IgFollowing());
        igFollowing.setQuery(source.getQuery());
        igFollowing.setTimestamp(ZonedDateTime.parse(source.getTimestamp(), DateUtil.ISO_INSTANT_MILLISECOND));
        igFollowing.setError(source.getError());
        igFollowing.setProfileUrl(source.getProfileUrl());
        igFollowing.setUsername(source.getUsername());
        igFollowing.setFullName(source.getFullName());
        igFollowing.setImgUrl(source.getImgUrl());
        igFollowing.setInstagramId(source.getId());
        igFollowing.setPrivate(source.isPrivate());
        igFollowing.setVerified(source.isVerified());
        return igFollowing;
    }
}
