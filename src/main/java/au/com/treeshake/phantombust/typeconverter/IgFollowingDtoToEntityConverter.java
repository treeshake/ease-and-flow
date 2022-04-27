package au.com.treeshake.phantombust.typeconverter;

import au.com.treeshake.phantombust.dto.IgFollowingDto;
import au.com.treeshake.phantombust.entity.IgFollowing;
import au.com.treeshake.phantombust.repository.IgFollowingRepository;
import au.com.treeshake.phantombust.util.DateUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

@Component
public class IgFollowingDtoToEntityConverter implements Converter<IgFollowingDto, IgFollowing> {

    private final IgFollowingRepository repository;

    public IgFollowingDtoToEntityConverter(IgFollowingRepository repository) {
        this.repository = repository;
    }

    @Override
    public IgFollowing convert(@NotNull IgFollowingDto source) {
        IgFollowing igFollowing = repository.findOneByQueryAndUsername(source.getQuery(), source.getUsername()).orElse(new IgFollowing());
        try {
            if (StringUtils.isNotBlank(source.getTimestamp())) {
                igFollowing.setTimestamp(ZonedDateTime.parse(source.getTimestamp(), DateUtil.ISO_INSTANT_MILLISECOND));
            }
        } catch (DateTimeParseException e) {
            // Leave as null
        }
        igFollowing.setQuery(source.getQuery());
        igFollowing.setError(source.getError());
        igFollowing.setProfileUrl(source.getProfileUrl());
        igFollowing.setUsername(source.getUsername());
        igFollowing.setFullName(source.getFullName());
        igFollowing.setImgUrl(source.getImgUrl());
        try {
            if (StringUtils.isNotBlank(source.getId())) {
                igFollowing.setInstagramId(new BigInteger(source.getId()));
            }
        } catch (NumberFormatException e) {
            igFollowing.setInstagramId(BigInteger.ZERO);
        }
        igFollowing.setPrivate(BooleanUtils.toBoolean(source.getIsPrivate()));
        igFollowing.setVerified(BooleanUtils.toBoolean(source.getIsVerified()));
        return igFollowing;
    }
}
