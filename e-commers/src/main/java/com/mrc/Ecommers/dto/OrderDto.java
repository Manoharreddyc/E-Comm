package com.mrc.Ecommers.dto;

import lombok.Data;

@Data
public class OrderDto {
    private long uId;
    private long pid;
    private int quantity;
}
