package com.aman.productservice.service;

import com.aman.productservice.model.Category;
import com.aman.productservice.model.GenericProductDto;
import com.aman.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    GenericProductDto getProductById(Long id);

    GenericProductDto createProduct(GenericProductDto productDto);

    List<GenericProductDto> getProducts();

    GenericProductDto updateProductById(GenericProductDto productDto,Long id);

    GenericProductDto deleteProduct(Long id);

    List<String> getAllCategory();
}
