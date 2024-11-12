package io.mxsix.phantombust.repository;

import io.mxsix.phantombust.entity.IgUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface.
 */
@Repository
public interface IgUserRepository extends JpaRepository<IgUser, Long> {

    Optional<IgUser> findOneByUsername(String username);
}
