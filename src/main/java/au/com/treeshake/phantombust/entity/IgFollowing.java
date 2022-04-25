package au.com.treeshake.phantombust.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Entity(name = "ig_following")
@Getter
@Setter
public class IgFollowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String query;
    private ZonedDateTime timestamp;
    private String error;
    private String profileUrl;
    private String username;
    private String fullName;
    private String imgUrl;
    private BigInteger instagramId;
    private boolean isPrivate;
    private boolean isVerified;
}
