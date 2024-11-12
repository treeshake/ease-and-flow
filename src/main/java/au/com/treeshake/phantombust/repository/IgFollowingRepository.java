package au.com.treeshake.phantombust.repository;

import au.com.treeshake.phantombust.entity.IgFollowing;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface.
 */
@Repository
public interface IgFollowingRepository extends JpaRepository<IgFollowing, Long> {

    @Query(value = "select distinct(\"query\") from ig_following", nativeQuery = true)
    List<String> findAllDistinctOnQuery();

    default Map<String, String> lookupProfilesProcessed() {
        return findAllDistinctOnQuery().stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
    }
}

