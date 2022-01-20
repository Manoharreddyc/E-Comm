package com.mrc.userservice.service;

import com.mrc.userservice.dto.UserDto;

    public interface UserService {
    UserDto save(UserDto order);


    UserDto getProduct(long productId);
}
