package io.awiya.order.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.awiya.order.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItem {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long productId;
    @Transient
    private Product product;
    private double price;
    private int quantity;
    private double discount;
    @ManyToOne
    @JsonProperty(access = WRITE_ONLY)
    private Order order;

    public double getAmount() {
        return price * quantity * (1 - discount);
    }
}
