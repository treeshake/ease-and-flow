package au.com.treeshake.phantombust.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO Class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class IgProfileDto {
    private String query;
    private String error;
    private String profileUrl;
    private String publicEmail;
    private String contactPhoneNumber;
    private String profileName;
    private String fullName;
    private String bio;
    private String snapchat;
    private String followersCount;
    private String followingCount;
    private String instagramID;
    private String isBusinessAccount;
    private String isVerified;
    private String imageUrl;
    private String postsCount;
    private String timestamp;
    private String businessCategory;
    private String website;
    private String mailFound;
    private String mailFound2;
    private String mailFound3;

}
