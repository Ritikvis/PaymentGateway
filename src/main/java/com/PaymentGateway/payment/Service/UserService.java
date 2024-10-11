package com.PaymentGateway.payment.Service;

import com.PaymentGateway.payment.DTOS.UserDto;
import com.PaymentGateway.payment.Entity.User;
import com.PaymentGateway.payment.Rrepository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User AddUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAccountNumber(userDto.getAccountNumber());
        return userRepository.save(user);
    }
}
