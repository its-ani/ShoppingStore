package dev.anirudh.productservicenov24.services;

import dev.anirudh.productservicenov24.dtos.CreateProductRequestDto;
import dev.anirudh.productservicenov24.dtos.FakeStoreProductDto;
import dev.anirudh.productservicenov24.exceptions.ProductNotFoundException;
import dev.anirudh.productservicenov24.models.Category;
import dev.anirudh.productservicenov24.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate; //using this we will be able to call 3rd party APIs.

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtoArray = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        if(fakeStoreProductDtoArray.length == 0){
            return new ArrayList<>();
        }

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtoArray){
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;
    }

    public Product getSingleProduct(long id) {
//        https://fakestoreapi.com/products/1
//        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if(fakeStoreProductDtoResponseEntity.getStatusCode() != HttpStatusCode.valueOf(200)){
            //handle exception later on
        }

        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
//        if(fakeStoreProductDto == null){
//            throw new ProductNotFoundException("Product with id " + id + " is not found. It is invalid id");
//        }

        return fakeStoreProductDto.toProduct();
    }


    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);

        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class );

        return fakeStoreProductDto1.toProduct();
    }

    @Override
    public Product setDelete(long id) throws ProductNotFoundException {
        Product p = getSingleProduct(id);
//        p.setDelete(id);
//        productRepository.save(p);

        return p;
    }

    @Override
    public Page<Product> getProductsPaginated(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Page<Product> getProductsPaginated(Pageable pageable) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category getSingleCategory(String title) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Category getCategoryByTitle(String title) {
        return null;
    }
}
