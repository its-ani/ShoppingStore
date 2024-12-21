package dev.anirudh.productservicenov24.controllers;

import dev.anirudh.productservicenov24.dtos.CreateProductRequestDto;
import dev.anirudh.productservicenov24.dtos.ErrorDTO;
import dev.anirudh.productservicenov24.exceptions.ProductNotFoundException;
import dev.anirudh.productservicenov24.models.Product;
import dev.anirudh.productservicenov24.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class ProductController {

    public ProductService productService;

//    Injecting dependencies
    public ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/paginated")
    public List<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber,@RequestParam("pageSize") int pageSize){
//        Below function is sending page number and page size then creating pagable in service class.
//        Page<Product> productPage = productService.getProductsPaginated(pageNumber, pageSize);

        Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Product> allCustomers = productService.getProductsPaginated(pageRequest);

//        allCustomers was returning the page<product> hence converting the page<products> to list<Products>
//        return allCustomers;
        return allCustomers.hasContent() ? allCustomers.getContent() : Collections.emptyList();
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        Product p = productService.getSingleProduct(id);
        ResponseEntity<Product> responseEntity;
        if(p != null){
            responseEntity = new ResponseEntity<>(p, HttpStatus.OK);
        }
        else{
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){
        return productService.createProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getPrice());
    }

    @PostMapping("/setDelete/{id}")
    public Product deleteProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        return productService.setDelete(id);
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProductID(@PathVariable("id") long id) throws ProductNotFoundException{
        return productService.setDelete(id);
    }
}