package com.mrc.userservice.dto;

import com.mrc.userservice.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String address;
    private String city;
    private String state;
    private int pin;

    public AddressDto(Address a) {
        this.address = a.getAddress();
        this.city = a.getCity();
        this.state = a.getState();
        this.pin = a.getPin();
    }
}