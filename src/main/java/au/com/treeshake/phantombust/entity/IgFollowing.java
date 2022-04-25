package au.com.treeshake.phantombust.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

/**
 *     // https://www.instagram.com/_bridgetli/,2022-04-24T11:38:11.829Z,,https://www.instagram.com/vthelabel_,vthelabel_,V THE LABEL JEWELRY,https://scontent-lhr8-1.cdninstagram.com/v/t51.2885-19/57258374_580663322445954_6266172601270272000_n.jpg?stp=dst-jpg_s150x150&_nc_ht=scontent-lhr8-1.cdninstagram.com&_nc_cat=106&_nc_ohc=5-UZczxQlaAAX-EaJ48&edm=ALB854YBAAAA&ccb=7-4&oh=00_AT8u0_LLSZm2neFWxc38iLzXYMhL959bx0dZGqeGzyqxaQ&oe=626D02EA&_nc_sid=04cb80,5451659841,false,false
 */
@Entity
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
    private Long instagramId;
    private boolean isPrivate;
    private boolean isVerified;
}
