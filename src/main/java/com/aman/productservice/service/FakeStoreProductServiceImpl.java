package com.aman.productservice.service;
import com.aman.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import com.aman.productservice.model.GenericProductDto;
import com.aman.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductServiceImpl(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    private GenericProductDto convertFakeStoreProductDtoIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto)
    {
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        return product;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
      return convertFakeStoreProductDtoIntoGenericProduct(fakeStoreProductServiceClient.getProductById(id));
    }

    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreProductDtoIntoGenericProduct(fakeStoreProductServiceClient.createProduct(product));
    }



    @Override
    public GenericProductDto updateProductById(GenericProductDto product,Long id) {
      return convertFakeStoreProductDtoIntoGenericProduct(fakeStoreProductServiceClient.updateProductById(product,id));
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
       return convertFakeStoreProductDtoIntoGenericProduct(fakeStoreProductServiceClient.deleteProduct(id));

    }

    @Override
    public List<GenericProductDto> getProducts() {
        List<GenericProductDto> ans = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductServiceClient.getProducts())
        {
            ans.add(convertFakeStoreProductDtoIntoGenericProduct(fakeStoreProductDto));
        }
       return ans;
    }
    @Override
    public List<String> getAllCategory() {
        return fakeStoreProductServiceClient.getAllCategory();
    }




}
