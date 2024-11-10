package dev.anirudh.productservicenov24.dtos;

import dev.anirudh.productservicenov24.models.Category;
import dev.anirudh.productservicenov24.models.Product;
import dev.anirudh.productservicenov24.services.ProductService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FakeStoreProductDto {
    private  String title;
    private  String description;
    private  String image;
    private  String category;
    private  double price;


    public Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);
        Category category1 = new Category();
        category1.setTitle(category);
        product.setCategory(category1);

        return product;
    }

    public List<Product> toBulkProduct(List<FakeStoreProductDto> products){
        List<Product> productArrayList = new ArrayList<>();

        for (FakeStoreProductDto prod: products){
            productArrayList.add(prod.toProduct());
        }
        return productArrayList;
    }

}



