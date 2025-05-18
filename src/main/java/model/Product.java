package model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    // Constructors
    public Product() {}

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


}