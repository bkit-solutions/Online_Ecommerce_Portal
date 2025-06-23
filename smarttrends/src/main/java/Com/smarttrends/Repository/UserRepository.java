package Com.smarttrends.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Com.smarttrends.Entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);  // Check if user exists by email
    Optional<User> findByEmail(String email);  // Find user by email
}
