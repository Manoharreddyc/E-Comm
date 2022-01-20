package com.mrc.userservice.entity;

import com.mrc.userservice.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String address;
    private String city;
    private String state;
    private  int pin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    public Address(AddressDto a) {
        this.address = a.getAddress();
        this.city = a.getCity();
        this.state = a.getState();
        this.pin = a.getPin();
    }
}
