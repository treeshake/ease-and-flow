package au.com.treeshake.phantombust.typeconverter;

import au.com.treeshake.phantombust.dto.IgFollowingDto;
import au.com.treeshake.phantombust.entity.IgFollowing;
import au.com.treeshake.phantombust.util.FieldUtils;

import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter class.
 */
@Component
public class IgFollowingDtoToEntityConverter implements Converter<IgFollowingDto, IgFollowing> {

    @Override
    public IgFollowing convert(@NotNull IgFollowingDto source) {
        // IG Following table has millions of records, do not try and find an existing result - db constraints will be enforced if
        // trying to insert a record where query and username is duplicated.
        // IgFollowing igFollowing = repository.findOneByQueryAndUsername(source.getQuery(), source.getUsername()).orElse(new IgFollowing());
        IgFollowing igFollowing = new IgFollowing();
        igFollowing.setTimestamp(FieldUtils.parseNullableTimestamp(source.getTimestamp()));
        igFollowing.setQuery(source.getQuery());
        igFollowing.setError(source.getError());
        igFollowing.setProfileUrl(source.getProfileUrl());
        igFollowing.setUsername(source.getUsername());
        igFollowing.setFullName(source.getFullName());
        igFollowing.setImgUrl(source.getImgUrl());
        igFollowing.setInstagramId(FieldUtils.parseInstagramId(source.getId()));
        igFollowing.setPrivate(BooleanUtils.toBoolean(source.getIsPrivate()));
        igFollowing.setVerified(BooleanUtils.toBoolean(source.getIsVerified()));
        return igFollowing;
    }
}
