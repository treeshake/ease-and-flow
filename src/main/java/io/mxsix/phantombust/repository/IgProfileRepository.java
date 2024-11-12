package io.mxsix.phantombust.repository;

import io.mxsix.phantombust.entity.IgProfile;
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
