package au.com.treeshake.phantombust.repository;

import au.com.treeshake.phantombust.entity.IgFollowing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IgFollowingRepository extends JpaRepository<IgFollowing, Long> {

    Optional<IgFollowing> findOneByQueryAndUsername(String query, String username);
}

