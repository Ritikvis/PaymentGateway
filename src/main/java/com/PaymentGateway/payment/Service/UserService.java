package com.PaymentGateway.payment.Service;

import com.PaymentGateway.payment.Entity.User;
import com.PaymentGateway.payment.Rrepository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User AddUser(User user) {
        User user1 = new User();
        user.setUserName(user1.getUserName());
        user.setEmail(user1.getEmail());
        user.setAccountNumber(user1.getAccountNumber());
        return userRepository.save(user);
    }
}
