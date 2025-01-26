package com.avyay.homora.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.avyay.homora.dtos.UserDTO;
import com.avyay.homora.managers.UserManager;
import com.avyay.homora.requests.LoginRequest;
import com.avyay.homora.requests.UserRequest;
import com.avyay.homora.responses.LoginResponse;
import com.avyay.homora.utilities.EmailUtility;
import com.avyay.homora.utilities.JwtUtility;

@Service
public class UserService {

    @Autowired
    private UserManager userManager;

    @Autowired
    private EmailUtility emailUtility;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtility jwtUtility;

    public String signup(UserRequest userRequest) {
        UserDTO userDTO = userManager.getByEmail(userRequest.getEmail());

        if (userDTO != null) {
            throw new IllegalArgumentException("Email Already Exists");
        }

        UserDTO newUser = new UserDTO();
        newUser.setName(userRequest.getName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setPhone(userRequest.getPhone());
        newUser.setVerified(false);

        // Generate OTP
        String otp = String.valueOf(new Random().nextInt(9000) + 1000);
        newUser.setOtp(otp);
        newUser.setOtpGeneratedTime(LocalDateTime.now());

        // save User with OTP
        userManager.save(newUser);

        // send OTP via email
        emailUtility.sendEmail(newUser.getEmail(), "Homora Application Email Verification", "Your OTP is : " + otp);

        return "Signup Successful! Please verify your email.";
    }

    public String verifyOTP(String email, String otp) {
        UserDTO userDTO = userManager.getByEmail(email);

        if (userDTO == null) {
            throw new IllegalArgumentException("Email Not Found");
        }

        if (userDTO.getOtp().equals(otp)) {
            if (userDTO.getOtpGeneratedTime().isBefore(LocalDateTime.now().minusMinutes(10))) {
                throw new IllegalArgumentException("OTP Expired");
            }

            userDTO.setVerified(true);
            userDTO.setOtp(null);
            userDTO.setOtpGeneratedTime(null);

            userManager.save(userDTO);
            return "OTP Verified Successfully";
        } else {
            throw new IllegalArgumentException("Invalid OTP");
        }
    }

    public LoginResponse login(LoginRequest loginRequest) {
        UserDTO userDTO = userManager.getByEmail(loginRequest.getEmail());

        if (userDTO == null) {
            return new LoginResponse(404, "User Not Found", null);
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDTO.getPassword())) {
            return new LoginResponse(401, "Incorrect Password", null);
        }

        String token = jwtUtility.generateToken(userDTO.getEmail());
        return new LoginResponse(200, "Login Successful", token);
    }
}
