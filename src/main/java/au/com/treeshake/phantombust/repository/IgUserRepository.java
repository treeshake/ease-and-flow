package au.com.treeshake.phantombust.repository;

import au.com.treeshake.phantombust.entity.IgUser;
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
