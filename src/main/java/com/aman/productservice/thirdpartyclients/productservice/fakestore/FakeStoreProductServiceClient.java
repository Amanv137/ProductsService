package com.aman.productservice.thirdpartyclients.productservice.fakestore;

import com.aman.productservice.exception.NotFoundException;
import com.aman.productservice.model.GenericProductDto;
import com.aman.productservice.thirdpartyclients.productservice.ThirdpartProductService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Wrapper over FakestoreproductService API
// First we will write client and then product service
@Service
public class FakeStoreProductServiceClient implements ThirdpartProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private String specificrequesturl = "https://fakestoreapi.com/products/{id}";
    private String productRequestUrl = "https://fakestoreapi.com/products/";

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }



    @Override
    public FakeStoreProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificrequesturl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id: "+id+" does not exist");
        }
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestUrl, product, FakeStoreProductDto.class);
        return response.getBody();
    }



    @Override
    public FakeStoreProductDto updateProductById(GenericProductDto product,Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.execute(specificrequesturl, HttpMethod.PUT, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    @Override
    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate =  restTemplateBuilder.build();
        //restTemplate.delete(specificrequesturl,);
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.execute(specificrequesturl,HttpMethod.DELETE, requestCallback, responseExtractor,id);
        FakeStoreProductDto fakeStoreProductDto= response.getBody();

        return fakeStoreProductDto;

    }

    @Override
    public List<FakeStoreProductDto> getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestUrl,FakeStoreProductDto[].class);
        List<GenericProductDto> ans = new ArrayList<>();
        System.out.println(response.getBody());
       return Arrays.stream(response.getBody()).toList();
    }
    @Override
    public List<String> getAllCategory() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> response=restTemplate.getForEntity("https://fakestoreapi.com/products/categories",String[].class);
        List<String> ans= Arrays.stream(response.getBody()).toList();
        return ans;
    }
}
