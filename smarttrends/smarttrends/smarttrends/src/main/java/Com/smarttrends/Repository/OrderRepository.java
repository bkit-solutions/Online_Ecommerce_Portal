package Com.smarttrends.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import Com.smarttrends.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Additional query methods can be added here if needed
}
