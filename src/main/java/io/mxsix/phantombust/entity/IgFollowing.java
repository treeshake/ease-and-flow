package io.mxsix.phantombust.entity;

import java.math.BigInteger;
import java.time.ZonedDateTime;
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
@Entity(name = "ig_following")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class IgFollowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String query;
    private String error;
    private ZonedDateTime timestamp;
    private String profileUrl;
    private String username;
    private String fullName;
    private String imgUrl;
    private BigInteger instagramId;
    private boolean isPrivate;
    private boolean isVerified;
}
