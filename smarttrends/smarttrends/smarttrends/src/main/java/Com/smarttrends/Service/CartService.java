package Com.smarttrends.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.smarttrends.Entity.CartItem;
import Com.smarttrends.Entity.Product;
import Com.smarttrends.Entity.User;
import Com.smarttrends.Repository.CartItemRepository;
import Com.smarttrends.Repository.ProductRepository;
import Com.smarttrends.Repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartItemRepository cartItemRepository;
	
	public String addToCart(Long productId, Long userId, int quantity) {
		System.out.println("the quantity is"+quantity);
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

    Optional<CartItem> existingItem = cartItemRepository.findByUserAndProduct(user, product);

    if (existingItem.isPresent()) {
        CartItem item = existingItem.get();
        System.out.println("The item qunatity get is"+item.getQuantity()+"and qauntity is"+  quantity);
		item.setQuantity(item.getQuantity() + 1);
        cartItemRepository.save(item);
        return "added sucessfully";
    } else {
        CartItem newItem = new CartItem();
        newItem.setUser(user);
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        cartItemRepository.save(newItem);
        return "added sucessfully";
    }	}
	
	 public String removeFromCart(Long productId, Long userId) {
	        Optional<CartItem> cartItemOpt = cartItemRepository.findByUserIdAndProductId(userId, productId);

	        if (cartItemOpt.isPresent()) {
	            CartItem cartItem = cartItemOpt.get();
	            int currentQty = cartItem.getQuantity();

	            if (currentQty >=1) {
	                cartItem.setQuantity(currentQty - 1);
	                cartItemRepository.save(cartItem);
	                return "Product quantity decremented in cart";
	            } else {
	                cartItemRepository.delete(cartItem);
	                return "Product removed from cart";
	            }
	        } else {
	            return "Product not found in cart";
	        }
	    }
	 public int getCartQuantity(Long userId, Long productId) {
	        return cartItemRepository.findByUserIdAndProductId(userId, productId)
	                .map(CartItem::getQuantity)
	                .orElse(0);
	    }

    public List<CartItem> getCartItemsByUser(Long userId) {
    	return cartItemRepository.findByUserId(userId).stream()
                .filter(item -> item.getQuantity() > 0)
                .collect(Collectors.toList());
    }
    
    
    public String deleteCartItem(Long userId, Long productId) {
        // Retrieve the cart item using the repository method that returns Optional
        Optional<CartItem> optionalCartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

        // Check if the cart item is present
        if (!optionalCartItem.isPresent()) {
            throw new RuntimeException("Cart item not found for the given user and product.");
        }

        // If the cart item exists, delete it
        CartItem cartItem = optionalCartItem.get();
        cartItemRepository.delete(cartItem);

        // Optionally, update any other necessary data or do additional processing here

        return "Item deleted successfully.";
    }

    }
