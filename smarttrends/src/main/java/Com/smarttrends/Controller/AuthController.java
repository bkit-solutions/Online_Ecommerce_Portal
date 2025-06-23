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
import java.util.Optional;

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
    
   

    // 1. Register User & Send OTP
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        String message = userService.registerUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    // 2. Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        boolean isVerified = userService.verifyOtp(email, otp);

        Map<String, Object> response = new HashMap<>();
        response.put("verified", isVerified);

        if (isVerified) {
            response.put("message", "OTP verified successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid or expired OTP.");
            return ResponseEntity.status(400).body(response);
        }
    }

    // 3. Resend OTP
    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        boolean otpSent = userService.resendOtp(email);

        Map<String, Object> response = new HashMap<>();
        if (otpSent) {
            response.put("message", "OTP resent successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "User not found.");
            return ResponseEntity.status(404).body(response);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();

        // Check if user exists and password matches
        boolean isAuthenticated = userService.loginUser(user.getEmail(), user.getPassword());

        if (isAuthenticated) {
            Optional<User> authenticatedUser = userRepository.findByEmail(user.getEmail());

            if (authenticatedUser.isPresent()) {
                User userObj = authenticatedUser.get();

                response.put("message", "Login successful!");
                response.put("role", userObj.getRole());
                response.put("userId", userObj.getId());
                response.put("name", userObj.getName());

                return ResponseEntity.ok(response);
            } else {
                response.put("message", "User not found!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } else {
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}


   
    

