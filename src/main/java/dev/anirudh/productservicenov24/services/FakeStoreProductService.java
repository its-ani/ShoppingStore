package dev.anirudh.productservicenov24.services;

import dev.anirudh.productservicenov24.dtos.CreateProductRequestDto;
import dev.anirudh.productservicenov24.dtos.FakeStoreProductDto;
import dev.anirudh.productservicenov24.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtoArray = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        if (fakeStoreProductDtoArray == null) {
            return new ArrayList<>();
        }
        List<FakeStoreProductDto> fakeStoreProductDtosList = Arrays.asList(fakeStoreProductDtoArray);
        return new FakeStoreProductDto().toBulkProduct(fakeStoreProductDtosList);
    }

    public Product getSingleProduct(long id){
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product createProduct(CreateProductRequestDto createProductRequestDto){
        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products", createProductRequestDto, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }

}
