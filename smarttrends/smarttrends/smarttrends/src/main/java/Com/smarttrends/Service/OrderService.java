package Com.smarttrends.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Com.smarttrends.Entity.CartItem;
import Com.smarttrends.Entity.Order;
import Com.smarttrends.Entity.User;
import Com.smarttrends.Repository.CartItemRepository;
import Com.smarttrends.Repository.OrderRepository;
import Com.smarttrends.Repository.ProductRepository;
import Com.smarttrends.Repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	 public List<Order> getAllOrders() {
	        return orderRepository.findAll();
	    }
	public Order placeOrder(Long userId, List<Long> cartItemIds, double totalAmount) {
	    Order order = new Order();
	    order.setTotalAmount(totalAmount);
	    order.setStatus("PLACED");

	    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	    order.setUser(user);
	      

	    Set<CartItem> cartItems = new HashSet<>(cartItemRepository.findAllById(cartItemIds));
	    order.setCartItems(cartItems);
	    for (CartItem item : cartItems) {
	        item.setQuantity(0);
	        cartItemRepository.save(item); // persist changes
	    }
	    
	    
	    SimpleMailMessage message = new SimpleMailMessage();
	 // Set multiple recipients (e.g., user and admin email)
	    message.setTo(new String[] { user.getEmail(), "jagadeeshthota75@gmail.com" });

	    // Subject
	    message.setSubject("🛒 Order Confirmation – Thank You for Your Purchase!");

	    // Email content
	    StringBuilder emailContent = new StringBuilder();
	    emailContent.append("Hi ").append(user.getName()).append(",\n\n");
	    emailContent.append("Thank you for shopping with us at the Amazon portal! 🎉\n\n");
	    emailContent.append("We’re happy to let you know that your order has been successfully placed.\n");
	    emailContent.append("Here are the details of your order:\n\n");
	    emailContent.append("📦 Total Amount: ₹").append(String.format("%.2f", totalAmount)).append("\n");
	    emailContent.append("📅 Order Date: ").append(java.time.LocalDate.now()).append("\n\n");
	    emailContent.append("You’ll receive another email once your items are shipped.\n");
	    emailContent.append("To view your order history or track the order, please log in to your account.\n\n");
	    emailContent.append("If you have any questions or concerns, feel free to reach out to our support team.\n\n");
	    emailContent.append("Thank you again for choosing us!\n");
	    emailContent.append("– Amazon Portal Team");

	    // Set content and send email
	    message.setText(emailContent.toString());
	    javaMailSender.send(message);
	 
	    return orderRepository.save(order);
	}
	


}
