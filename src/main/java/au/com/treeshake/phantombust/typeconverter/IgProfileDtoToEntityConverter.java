package au.com.treeshake.phantombust.typeconverter;

import au.com.treeshake.phantombust.dto.IgProfileDto;
import au.com.treeshake.phantombust.entity.IgProfile;
import au.com.treeshake.phantombust.repository.IgProfileRepository;
import au.com.treeshake.phantombust.util.FieldUtils;
import java.math.BigInteger;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter class.
 */
@Component
public record IgProfileDtoToEntityConverter(
        IgProfileRepository repository) implements Converter<IgProfileDto, IgProfile> {

    @Override
    public IgProfile convert(@NotNull IgProfileDto source) {
        BigInteger instagramId = FieldUtils.parseInstagramId(source.getInstagramID());
        IgProfile igProfile = repository.findOneByInstagramID(instagramId).orElse(new IgProfile());
        igProfile.setProfileUrl(source.getProfileUrl());
        igProfile.setPublicEmail(source.getPublicEmail());
        igProfile.setContactPhoneNumber(source.getContactPhoneNumber());
        igProfile.setProfileName(source.getProfileName());
        igProfile.setFullName(source.getFullName());
        igProfile.setBio(source.getBio());
        igProfile.setSnapchat(source.getSnapchat());
        igProfile.setFollowersCount(FieldUtils.parseNullableInteger(source.getFollowersCount()));
        igProfile.setFollowingCount(FieldUtils.parseNullableInteger(source.getFollowingCount()));
        igProfile.setInstagramID(instagramId);
        igProfile.setBusinessAccount(BooleanUtils.toBoolean(source.getIsBusinessAccount()));
        igProfile.setVerified(BooleanUtils.toBoolean(source.getIsVerified()));
        igProfile.setImageUrl(source.getImageUrl());
        igProfile.setPostsCount(FieldUtils.parseNullableInteger(source.getPostsCount()));
        igProfile.setQuery(source.getQuery());
        igProfile.setTimestamp(FieldUtils.parseNullableTimestamp(source.getTimestamp()));
        igProfile.setBusinessCategory(source.getBusinessCategory());
        igProfile.setWebsite(source.getWebsite());
        igProfile.setMailFound(source.getMailFound());
        igProfile.setError(source.getError());
        igProfile.setMailFound2(source.getMailFound2());
        igProfile.setMailFound3(source.getMailFound3());
        return igProfile;
    }
}
