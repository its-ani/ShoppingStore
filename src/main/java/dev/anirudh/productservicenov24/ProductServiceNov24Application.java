package dev.anirudh.productservicenov24;

import dev.anirudh.productservicenov24.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductServiceNov24Application {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceNov24Application.class, args);
    }
}