package au.com.treeshake.phantombust.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO Class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class IgUserDto {
    private String username;
}
