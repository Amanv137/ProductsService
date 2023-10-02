package com.aman.productservice.controller;

import com.aman.productservice.exception.NotFoundException;
import com.aman.productservice.model.Category;
import com.aman.productservice.model.ExceptionDto;
import com.aman.productservice.model.GenericProductDto;
import com.aman.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products/")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService=productService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts() {
//        Returning Empty list of generic product Dto
//        return  List.of(new GenericProductDto(),
//                new GenericProductDto());

       return productService.getProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException{
       return productService.getProductById(id);

    }


    @PostMapping
    public GenericProductDto createProduct(@RequestBody  GenericProductDto product) {
//        System.out.println("product "+product.toString());
//        return "Created a new product with id: "+ UUID.randomUUID();
        return productService.createProduct(product);

    }

    @PutMapping("{id}")
    public ResponseEntity<GenericProductDto> updateProductById(@RequestBody GenericProductDto requestBody,
                                      @PathVariable("id") Long id) {

        ResponseEntity<GenericProductDto> responseEntity = new ResponseEntity<>(
                productService.updateProductById(requestBody,id),
                HttpStatus.OK
        );
        return responseEntity;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProduct(@PathVariable("id") Long id) {
//        System.out.println("product "+product.toString());
//        return "Created a new product with id: "+ UUID.randomUUID();
        ResponseEntity<GenericProductDto> responseEntity = new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
        return responseEntity;

    }

    @GetMapping("categories")
    public List<String> getAllCategories()
    {
//        ResponseEntity<List<Category>> responseEntity=new ResponseEntity<>(
//                productService.getAllCategory(),
//                HttpStatus.OK
//                );
//        return responseEntity;
        return  productService.getAllCategory();
    }

//    @ExceptionHandler(NotFoundException.class)
//    private void handleNotFoundException()
//    {
//        System.out.println("Not Found exception");
//    }

//    @ExceptionHandler(NotFoundException.class)
//    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException)
//    {
//        return new ResponseEntity<>(
//               // new ExceptionDto(HttpStatus.NOT_FOUND,"Not Found exception"),
//                new ExceptionDto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),
//                HttpStatus.NOT_FOUND);
//    }
}
