package dev.anirudh.productservicenov24.services;

import dev.anirudh.productservicenov24.dtos.CreateProductRequestDto;
import dev.anirudh.productservicenov24.exceptions.ProductNotFoundException;
import dev.anirudh.productservicenov24.models.Category;
import dev.anirudh.productservicenov24.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(long id) throws ProductNotFoundException;
    Product createProduct(String title,
                          String description,
                          String image,
                          String category,
                          double price);

    Product setDelete(long id) throws ProductNotFoundException;

    Page<Product> getProductsPaginated(int pageNumber, int pageSize);
    Page<Product> getProductsPaginated(Pageable pageable);
    List<Category> getAllCategories();
    Category getSingleCategory(String title) throws ProductNotFoundException;
}
