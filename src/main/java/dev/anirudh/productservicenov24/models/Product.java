package dev.anirudh.productservicenov24.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Line 8-11 are known as Spring annotations. These are imported from lombok.

public class Product {
    private int id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;
}
