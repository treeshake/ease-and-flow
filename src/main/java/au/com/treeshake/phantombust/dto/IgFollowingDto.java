package au.com.treeshake.phantombust.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({"query",
        "timestamp",
        "error",
        "profileUrl",
        "username",
        "fullName",
        "imgUrl",
        "id",
        "isPrivate",
        "isVerified"
})
@Data
public class IgFollowingDto {

    private String query;
    private String timestamp;
    private String error;
    private String profileUrl;
    private String username;
    private String fullName;
    private String imgUrl;
    private Long id;
    private boolean isPrivate;
    private boolean isVerified;
}
