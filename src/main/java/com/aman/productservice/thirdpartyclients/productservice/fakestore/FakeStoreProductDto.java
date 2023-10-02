package com.aman.productservice.thirdpartyclients.productservice.fakestore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// created for 3rd party API we are using
@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;


}
