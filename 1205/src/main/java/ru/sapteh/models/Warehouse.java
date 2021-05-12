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
@Table
public class Warehouse {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String address;

    @OneToMany(mappedBy = "warehouse")
    Set<Product> products;

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
