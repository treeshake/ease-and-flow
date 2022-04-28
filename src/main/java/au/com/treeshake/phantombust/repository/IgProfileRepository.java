package au.com.treeshake.phantombust.repository;

import au.com.treeshake.phantombust.entity.IgProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IgProfileRepository extends JpaRepository<IgProfile, Long> {
    Optional<IgProfile> findOneByProfileName(String profileName);
}
