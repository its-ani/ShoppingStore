package dev.anirudh.productservicenov24.services;

import dev.anirudh.productservicenov24.dtos.CreateProductRequestDto;
import dev.anirudh.productservicenov24.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(long id);
    Product createProduct(CreateProductRequestDto createProductRequestDto);
}
