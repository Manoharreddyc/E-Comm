package com.mrc.userservice.controller;

import com.mrc.userservice.dto.UserDto;
import com.mrc.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/save")
    public UserDto save(@RequestBody UserDto request){
        return userService.save(request);
    }

    @GetMapping("/user")
    public UserDto getProduct(@RequestParam("productId") long productId){
        return userService.getProduct(productId);
    }


}
