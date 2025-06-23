package Com.smarttrends.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Com.smarttrends.Entity.User;
import Com.smarttrends.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JavaMailSender javaMailSender;

    public String registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "User already exists!";
        }
        else {
        userRepository.save(user);
     // Send a registration confirmation email
     // Send a registration confirmation email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Welcome to Amazon Portal – Registration Successful");

        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Hi ").append(user.getName()).append(",\n\n");
        emailContent.append("🎉 Welcome aboard! Your registration to the Amazon-like E-Commerce portal was successful.\n\n");
        emailContent.append("Here are your login credentials:\n");
        emailContent.append("📧 Email: ").append(user.getEmail()).append("\n");
        emailContent.append("🔐 Password: ").append(user.getPassword()).append("\n\n");
        emailContent.append("You can now log in and start exploring our products, adding items to your cart, and placing orders!\n\n");
        emailContent.append("If you have any questions, feel free to reply to this email or contact our support team.\n\n");
        emailContent.append("Thank you,\n");
        emailContent.append("Amazon Portal Team");

        message.setText(emailContent.toString());
        javaMailSender.send(message);

        return "User registered successfully!";
    }
   }

    public Boolean loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
