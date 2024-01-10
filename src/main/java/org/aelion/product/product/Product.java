package org.aelion.product.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String id;

    private String label;

    private  int stock;

    public void setStock(int stock) {
        if(stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stock = stock;
    }
}
