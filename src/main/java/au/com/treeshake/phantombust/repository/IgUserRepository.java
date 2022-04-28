package au.com.treeshake.phantombust.repository;

import au.com.treeshake.phantombust.entity.IgUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IgUserRepository extends JpaRepository<IgUser, Long> {

    Optional<IgUser> findOneByUsername(String username);
}
