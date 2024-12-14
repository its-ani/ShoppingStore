package dev.anirudh.productservicenov24.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Line 8-11 are known as Spring annotations. These are imported from lombok.
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    @ManyToOne(cascade = {CascadeType.PERSIST})
//    @JoinColumn(name = "category_id")
    private Category category;

    public void setDelete(long id){
        this.setIsDeleted(true);
    }
}