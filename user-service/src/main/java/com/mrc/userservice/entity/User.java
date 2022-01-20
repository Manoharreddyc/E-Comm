package com.mrc.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Name;
    private int age;
    private long phno;
    private String email;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    List<Address> address;


}
