package com.aman.productservice.thirdpartyclients.productservice;

import com.aman.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import com.aman.productservice.model.GenericProductDto;

import java.util.List;

public interface ThirdpartProductService {

    FakeStoreProductDto getProductById(Long id);

    FakeStoreProductDto createProduct(GenericProductDto productDto);

    List<FakeStoreProductDto> getProducts();

    FakeStoreProductDto updateProductById(GenericProductDto productDto,Long id);

    FakeStoreProductDto deleteProduct(Long id);

    List<String> getAllCategory();
}
