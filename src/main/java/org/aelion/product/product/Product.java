package org.aelion.product.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    @NotNull(message = "Id cannot be null")
    @NotBlank(message = "Id cannot be blank")
    private String id;

    @NotNull(message = "Label cannot be null")
    @NotBlank(message = "Label cannot be blank")
    private String label;

    @Min(value = 0, message = "The value must be positive")
    private int stock;

    public void setStock(int stock) {
        if(stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stock = stock;
    }
}
