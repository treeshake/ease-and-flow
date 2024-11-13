package au.com.treeshake.phantombust.entity;

import au.com.treeshake.phantombust.model.CsvMetadata;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class.
 */
@Entity(name = "ig_profile")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class IgProfile extends CsvMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String query;
    private String error;
    private String profileUrl;
    private String publicEmail;
    private String contactPhoneNumber;
    @Column(name = "username")
    private String profileName;
    private String fullName;
    private String bio;
    private String snapchat;
    private Integer followersCount;
    private Integer followingCount;
    @Column(name = "instagram_id")
    private BigInteger instagramID;
    private boolean isBusinessAccount;
    private boolean isVerified;
    private String imageUrl;
    private Integer postsCount;
    private ZonedDateTime timestamp;
    private String businessCategory;
    private String website;
    private String mailFound;
    private String mailFound2;
    private String mailFound3;

}
