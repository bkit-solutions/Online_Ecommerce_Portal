package Com.smarttrends.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        User existingUser = repo.findByEmail(updateUser.getEmail());

        if (existingUser == null) {
            return "User not found with email: " + updateUser.getEmail();
        }

        // Update only mutable fields
        existingUser.setName(updateUser.getName());
        existingUser.setPassword(updateUser.getPassword());

        // Save the updated user
        repo.save(existingUser);

        return "User updated successfully";
    }

 }
