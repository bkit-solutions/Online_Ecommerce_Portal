package Com.smarttrends.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Com.smarttrends.Dtos.OrderDTO;
import Com.smarttrends.Entity.Order;
import Com.smarttrends.Service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping()
	public ResponseEntity<String> placeOrder(@RequestBody OrderDTO orderDTO) {
		System.out.println("The user id is"+orderDTO.getUserId()+"the cartitemids"+orderDTO.getCartItemIds()+"The total amount"+orderDTO.getTotalAmount());
	    orderService.placeOrder(orderDTO.getUserId(), orderDTO.getCartItemIds(), orderDTO.getTotalAmount());
	    return new ResponseEntity<>("Order placed successfully!", HttpStatus.CREATED);
	}

	@GetMapping
	public List<Order> getOrderHistory() {
	    return orderService.getAllOrders();
	}

}
