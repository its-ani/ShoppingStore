package dev.anirudh.productservicenov24.repositiories;

import dev.anirudh.productservicenov24.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);
}
