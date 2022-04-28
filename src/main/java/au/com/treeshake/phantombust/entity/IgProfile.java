package au.com.treeshake.phantombust.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Entity(name = "ig_profile")
@Getter
@Setter
public class IgProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private String query;
    private ZonedDateTime timestamp;
    private String businessCategory;
    private String website;
    private String mailFound;
    private String error;
    private String mailFound2;
    private String mailFound3;

}
