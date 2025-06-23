package Com.smarttrends.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Carts")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private int quantity;

	public CartItem() {
	}

	public CartItem(Product product, User user, int quantity) {
	    this.product = product;
	    this.user = user;
	    this.quantity = quantity;
	}

	// Getters and Setters
	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

	public Product getProduct() {
	    return product;
	}

	public void setProduct(Product product) {
	    this.product = product;
	}

	public User getUser() {
	    return user;
	}

	public void setUser(User user) {
	    this.user = user;
	}

	public int getQuantity() {
	    return quantity;
	}

	public void setQuantity(int quantity) {
	    this.quantity = quantity;
	}
    
}
