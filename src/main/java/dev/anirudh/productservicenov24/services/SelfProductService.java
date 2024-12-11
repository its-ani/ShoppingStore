package dev.anirudh.productservicenov24.services;

import dev.anirudh.productservicenov24.exceptions.ProductNotFoundException;
import dev.anirudh.productservicenov24.models.Category;
import dev.anirudh.productservicenov24.models.Product;
import dev.anirudh.productservicenov24.repositiories.CategoryRepository;
import dev.anirudh.productservicenov24.repositiories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

//    Below lines are injecting dependencies
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

//    public List<Product> findByCategory() {}


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            throw new ProductNotFoundException("Product with id "+ id + "is not present in the database.");
        }
        return product.get();
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);

        Category categoryFromDB = categoryRepository.findByTitle(category);
        if(categoryFromDB == null) {
            Category newCategory = new Category();
            newCategory.setTitle(category);

            categoryFromDB = newCategory;
        }

        product.setCategory(categoryFromDB);
        Product createdProduct =  productRepository.save(product);

        return createdProduct;
    }
}
