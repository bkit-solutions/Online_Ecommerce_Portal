package Com.smarttrends.Entity;


import java.util.ArrayList;
import java.util.HashSet;

import jakarta.persistence.UniqueConstraint;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double totalAmount;
	private String status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany
	@JoinTable(
	    name = "orders_cart_items",
	    joinColumns = @JoinColumn(name = "order_id"),
	    inverseJoinColumns = @JoinColumn(name = "cart_items_id"),
	    uniqueConstraints = @UniqueConstraint(columnNames = {"order_id", "cart_items_id"})
	)
	private Set<CartItem> cartItems = new HashSet<>();

	// Getters and Setters

	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

	public double getTotalAmount() {
	    return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
	    this.totalAmount = totalAmount;
	}

	public String getStatus() {
	    return status;
	}

	public void setStatus(String status) {
	    this.status = status;
	}

	public User getUser() {
	    return user;
	}

	public void setUser(User user) {
	    this.user = user;
	}

	public Set<CartItem> getCartItems() {
	    return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
	    this.cartItems = cartItems;
	}
    
}
