package Com.smarttrends.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Com.smarttrends.Dtos.UserDTO;
import Com.smarttrends.Entity.User;
import Com.smarttrends.Repository.UserRepository;
import Com.smarttrends.Service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    
    
    @GetMapping("/test")
    public String test() {
        return "API is working!";
    }
    
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.registerUser(user);
       
        
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        // Assuming you have a service method that checks login credentials
        boolean isAuthenticated = userService.loginUser(user.getEmail(), user.getPassword());
        
        Map<String, Object> response = new HashMap<>();
        
        if (isAuthenticated) {
            // Assuming your user object has a role field
            User authenticatedUser = userRepository.findByEmail(user.getEmail()); // Fetch user by email
            
            // Add relevant data to response
            response.put("message", "Login successful!");
            response.put("role", authenticatedUser.getRole());
            response.put("userId", authenticatedUser.getId());
            response.put("name", authenticatedUser.getName());
            // Send role in response
            
            return ResponseEntity.ok(response); // Return success with role
        } else {
            response.put("message", "Invalid credentials!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // Return failure with HTTP status 401
        }
    }

   
    
}
