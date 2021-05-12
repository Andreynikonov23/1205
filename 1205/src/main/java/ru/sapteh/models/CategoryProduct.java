package ru.sapteh.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "category_product")
public class CategoryProduct {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

    @OneToMany(mappedBy = "categoryProduct")
    Set<Product> products;

    @Override
    public String toString() {
        return "CategoryProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
