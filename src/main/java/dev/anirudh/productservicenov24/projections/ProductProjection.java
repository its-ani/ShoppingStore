package dev.anirudh.productservicenov24.projections;

public interface ProductProjection {
    String getTitle();
    Long getId();
    String getDescription();
    String getImageUrl();
    String getPrice();
    String getCategory();
}
