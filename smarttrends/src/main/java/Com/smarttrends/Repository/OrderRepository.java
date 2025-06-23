package Com.smarttrends.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Com.smarttrends.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUserId(Long userId);
    // Additional query methods can be added here if needed
}
