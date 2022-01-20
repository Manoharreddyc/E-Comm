package com.mrc.Ecommers.entity;

import com.mrc.Ecommers.dto.OrderDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String trackId;
    private double totalAmount;
    private String  address;

    @OneToMany(cascade=CascadeType.ALL)
            @JoinColumn(name = "order_id",referencedColumnName = "id")
    List<Item> items;


}
