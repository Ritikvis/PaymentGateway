package com.PaymentGateway.payment.Controller;

import com.PaymentGateway.payment.DTOS.UserDto;
import com.PaymentGateway.payment.Entity.User;
import com.PaymentGateway.payment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("User")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("addUser")
    public ResponseEntity<User> AddUser(@RequestBody UserDto userDto){
        User user = userService.AddUser(userDto);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

}
