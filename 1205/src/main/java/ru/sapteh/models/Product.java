package ru.sapteh.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product {

    public Product(String name, String cost) {
        this.name = name;
        this.cost = cost;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String cost;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "warehouse_id")
    private Warehouse warehouse;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "category_product_id")
    private CategoryProduct categoryProduct;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                ", warehouse=" + warehouse +
                ", categoryProduct=" + categoryProduct +
                '}';
    }
}
