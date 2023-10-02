package com.aman.productservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class GenericProductDto {
    private Long id;
    private  String title;
    private String description;
    private String image;
    private String category;
    private double price;
}

