package io.mxsix.phantombust.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO Class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class IgFollowingDto {
    private String query;
    private String error;
    private String timestamp;
    private String profileUrl;
    private String username;
    private String fullName;
    private String imgUrl;
    private String id;
    private String isPrivate;
    private String isVerified;
}
