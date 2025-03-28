package com.aithinkers.GoldenGym.service;



import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aithinkers.GoldenGym.entity.OtpVerification;
import com.aithinkers.GoldenGym.entity.User;
import com.aithinkers.GoldenGym.repo.OtpVerificationRepository;

@Service
public class OtpService {

    private final OtpVerificationRepository otpVerificationRepository;
    private final EmailService emailService;

    @Autowired
    public OtpService(OtpVerificationRepository otpVerificationRepository, EmailService emailService) {
        this.otpVerificationRepository = otpVerificationRepository;
        this.emailService = emailService;
    }

    public String generateOtp() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

    public OtpVerification createOtp(User user) {
        String otp = generateOtp();

        // Save OTP in DB
        OtpVerification otpVerification = new OtpVerification();
        otpVerification.setUser(user);
        otpVerification.setOtp(otp);
        otpVerification.setCreatedAt(LocalDateTime.now());

        otpVerificationRepository.save(otpVerification);

        // ✅ **Send OTP via Email**
        emailService.sendOtpEmail(user.getEmail(), otp);

        return otpVerification;
    }

    public boolean verifyOtp(User user, String otp) {
        Optional<OtpVerification> otpVerification = otpVerificationRepository.findByUser(user);

        if (otpVerification.isPresent()) {
            OtpVerification storedOtp = otpVerification.get();

            // ✅ **Check if OTP is expired (Valid for 5 minutes)**
            if (storedOtp.getCreatedAt().plusMinutes(5).isBefore(LocalDateTime.now())) {
                otpVerificationRepository.delete(storedOtp); // Remove expired OTP
                return false; // OTP Expired
            }

            // ✅ **Verify OTP**
            if (storedOtp.getOtp().equals(otp)) {
                otpVerificationRepository.delete(storedOtp); // Remove OTP after use
                return true;
            }
        }

        return false;
    }
}
