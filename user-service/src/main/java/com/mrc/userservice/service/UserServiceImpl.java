package com.mrc.userservice.service;

import com.mrc.userservice.dto.UserDto;
import com.mrc.userservice.entity.Address;
import com.mrc.userservice.entity.User;
import com.mrc.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto save(UserDto userdto) {
        // take all user data from user service by using feign client like addresss user details
        User u = new User();
        u.setName(userdto.getName());
        u.setAge(userdto.getAge());
        u.setEmail(userdto.getEmail());
        u.setPhno(userdto.getPhno());


        u.setAddress(userdto.getAddress().stream().map(a -> new Address(a)).collect(Collectors.toList()));
        User us = userRepository.save(u);
        return preparedResponse(userRepository.save(us));
    }

    public UserDto preparedResponse(User user) {
        if(null==user){
          throw new RuntimeException("Usr not found")  ;
        }

        return new UserDto(user);
    }

    @Override
    public UserDto getProduct(long productId) {
        Optional<User> u = userRepository.findById(productId);
        return preparedResponse(u.get());
    }
}
