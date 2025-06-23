package Com.smarttrends.Dtos;

import java.util.List;

public class OrderDTO {

	private Long userId;
    private List<Long> cartItemIds;
    private double totalAmount;
    private String address;

    // Default constructor
    public OrderDTO() {
    }

    // All-args constructor
    public OrderDTO(Long userId, List<Long> cartItemIds, double totalAmount) {
        this.userId = userId;
        this.cartItemIds = cartItemIds;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getCartItemIds() {
        return cartItemIds;
    }

    public void setCartItemIds(List<Long> cartItemIds) {
        this.cartItemIds = cartItemIds;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
   }
