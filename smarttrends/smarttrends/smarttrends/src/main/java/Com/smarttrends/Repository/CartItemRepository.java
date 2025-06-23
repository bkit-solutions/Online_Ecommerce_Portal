package Com.smarttrends.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Com.smarttrends.Entity.CartItem;
import Com.smarttrends.Entity.Product;
import Com.smarttrends.Entity.User;
import jakarta.transaction.Transactional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	 Optional<CartItem> findByUserAndProduct(User user, Product product);

	List<CartItem> findByUserId(Long userId);
	
	Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);

}
