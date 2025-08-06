package ecom_web.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String description;
    private Double price;

    @ManyToOne
    private Category category;
    private String title;
    private String image;
}
