package Com.smarttrends.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

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

    

    // Register user and send OTP
    public String registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "User already exists!";
        }

        // Generate 6-digit OTP
        String otp = generateOtp();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5); // 5 minutes validity

        user.setOtp(otp);
        user.setOtpExpiry(expiryTime);
        user.setVerified(false); // default not verified

        userRepository.save(user);

        sendOtpEmail(user.getEmail(), user.getName(), otp);

        return "OTP sent to your email. Please verify to complete registration.";
    }

    // OTP verification
    public boolean verifyOtp(String email, String otp) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getOtp() != null &&
                user.getOtp().equals(otp) &&
                user.getOtpExpiry() != null &&
                LocalDateTime.now().isBefore(user.getOtpExpiry())) {

                user.setVerified(true);
                user.setOtp(null); // clear OTP after successful verification
                user.setOtpExpiry(null);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    // Resend OTP
    public boolean resendOtp(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            String otp = generateOtp();
            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

            user.setOtp(otp);
            user.setOtpExpiry(expiryTime);
            userRepository.save(user);

            sendOtpEmail(user.getEmail(), user.getName(), otp);

            return true;
        }

        return false;
    }

    // Generate 6-digit OTP
    private String generateOtp() {
        Random random = new Random();
        int otpInt = 100000 + random.nextInt(900000);
        return String.valueOf(otpInt);
    }

    // Email with OTP
    private void sendOtpEmail(String toEmail, String name, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP for Smart-Trends Registration");

        String emailBody = String.format("""
                Hello %s,

                👋 Welcome to Smart-Trends E-Commerce!

                Please use the following OTP to verify your account:
                🔐 OTP: %s

                This OTP is valid for 5 minutes.

                If you didn’t request this, please ignore this email.

                Best Regards,
                Smart-Trends Team
                """, name, otp);

        message.setText(emailBody);
        javaMailSender.send(message);
    }

    public Boolean loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return true;
        }
        return false;
    }

}
