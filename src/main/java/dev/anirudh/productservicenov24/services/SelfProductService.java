package dev.anirudh.productservicenov24.services;

import dev.anirudh.productservicenov24.exceptions.ProductNotFoundException;
import dev.anirudh.productservicenov24.models.Category;
import dev.anirudh.productservicenov24.models.Product;
import dev.anirudh.productservicenov24.repositiories.CategoryRepository;
import dev.anirudh.productservicenov24.repositiories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllActiveProducts();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Page<Product> getProductsPaginated(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Product> getProductsPaginated(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Category getSingleCategory(String title) throws ProductNotFoundException {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findByTitle(title));
        if (category.isEmpty()) {
            throw new ProductNotFoundException("Category with title " + title + " is not present in the database.");
        }
        return category.get();
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findActiveById(id);
        if(product.isEmpty()) {
            throw new ProductNotFoundException("Product with id "+ id + " is not present in the database.");
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

    @Override
    public Product setDelete(long id) throws ProductNotFoundException {
        Product p = getSingleProduct(id);
        p.setDelete(id);
        productRepository.save(p);

        return p;
    }
}
