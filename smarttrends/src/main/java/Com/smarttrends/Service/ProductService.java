package Com.smarttrends.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Com.smarttrends.Dtos.ProductDTO;
import Com.smarttrends.Entity.Product;
import Com.smarttrends.Entity.User;
import Com.smarttrends.Repository.ProductRepository;
import Com.smarttrends.Repository.UserRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JavaMailSender mailSender;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(ProductDTO dto) {
        Product product = new Product(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getCategory(), dto.getImageUrl());
        Product savedProduct = productRepository.save(product);

        // Fetch all users
        List<User> users = userRepo.findAll();

        if (users.isEmpty()) {
            System.out.println("⚠️ No users found in the database.");
        } else {
            System.out.println("✅ Users fetched: " + users.size());
        }

        // Send email to each user
        for (User user : users) {
            System.out.println("📨 Sending email to: " + user.getEmail());
            sendProductEmail(user.getEmail(), savedProduct);
        }

        return savedProduct;
    }

    private void sendProductEmail(String toEmail, Product product) {
        String subject = "🆕 New Product Added: " + product.getName();
        String message = String.format(
            "Hello,\n\nA new product has just been added:\n\n" +
            "📦 Product: %s\n" +
            "💬 Description: %s\n" +
            "💲 Price: $%.2f\n" +
            "🏷️ Category: %s\n\n" +
            "Visit our store to explore more!",
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getCategory()
        );

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(toEmail);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            mailSender.send(mailMessage);
        } catch (Exception e) {
            System.out.println("❌ Failed to send email to " + toEmail + ": " + e.getMessage());
        }
    }
}
