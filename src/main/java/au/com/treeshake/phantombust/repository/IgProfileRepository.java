package au.com.treeshake.phantombust.repository;

import au.com.treeshake.phantombust.entity.IgProfile;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface.
 */
@Repository
public interface IgProfileRepository extends JpaRepository<IgProfile, Long> {
    Optional<IgProfile> findOneByInstagramID(BigInteger instagramId);
}
