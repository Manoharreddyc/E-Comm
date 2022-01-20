package com.mrc.userservice.dto;

import com.mrc.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto{
    private String name;
    private int age;
    private long phno;
    private String email;
    List<AddressDto> address;

    public UserDto(User user) {
        this.name=user.getName();
        this.age = user.getAge();
        this.phno = user.getPhno();
        this.email = user.getEmail();
        this.address=user.getAddress().stream().map(a -> new AddressDto(a)).collect(Collectors.toList());
    }
}
