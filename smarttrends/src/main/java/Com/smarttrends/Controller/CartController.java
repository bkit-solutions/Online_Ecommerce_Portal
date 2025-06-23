package Com.smarttrends.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Com.smarttrends.Dtos.CartDeleteRequest;
import Com.smarttrends.Dtos.CartItemDTO;
import Com.smarttrends.Dtos.UpdateProfileDto;
import Com.smarttrends.Dtos.UserDTO;
import Com.smarttrends.Entity.CartItem;
import Com.smarttrends.Entity.User;
import Com.smarttrends.Repository.UserRepository;
import Com.smarttrends.Service.CartService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Autowired UserRepository repo;

    @PostMapping("/add")
    public String addToCart(@RequestBody CartItemDTO request) {
        return cartService.addToCart(request.getProductId(), request.getUserId(), request.getQuantity());
    }

    
    @PostMapping("/remove")
    public String removeFromCart(@RequestBody CartItemDTO request) {
        return cartService.removeFromCart(request.getProductId(), request.getUserId());
    }
    @GetMapping("/quantity/{userId}/{productId}")
    public int getCartQuantity(@PathVariable Long userId, @PathVariable Long productId) {
        return cartService.getCartQuantity(userId, productId);
    }
    
    @GetMapping("/{userId}")
    public List<CartItem> viewCart(@PathVariable Long userId) {
        return cartService.getCartItemsByUser(userId);
    }
    
    @PutMapping("/update")
    public String updateProfile(@RequestBody UserDTO updateUser) {
        // Find the existing user by email
        Optional<User> optionalUser = repo.findByEmail(updateUser.getEmail());

        if (optionalUser.isEmpty()) {
            return "User not found with email: " + updateUser.getEmail();
        }

        User existingUser = optionalUser.get();

        // Update only mutable fields
        existingUser.setName(updateUser.getName());
        existingUser.setPassword(updateUser.getPassword());

        // Save the updated user
        repo.save(existingUser);

        return "User updated successfully";
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFromCart(@RequestBody CartDeleteRequest request) {
        Long userId = request.getUserId();
        Long productId = request.getProductId();
        System.out.println("my user id is"+userId+"product id is"+productId);
        cartService.deleteCartItem(userId, productId);
        return ResponseEntity.ok("Item deleted successfully");
    }

 }
