package org.aelion.product.product;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl {

    private final List<Product> products = new ArrayList<>();

    public ProductServiceImpl(){
        products.add(new Product("1", "Product 1", 10));
        products.add(new Product("2", "Product 2", 20));
        products.add(new Product("3", "Product 3", 30));
   }
    public List<Product> findAll() {
        return products;
    }

    public void AddProduct(Product product) {
        products.stream().findAny().filter(p -> p.getId().equals(product.getId())).ifPresentOrElse(p -> {
            p.setStock(p.getStock() + product.getStock());
            if(p.getStock() < 0) {
                products.remove(p);
            }
        }, () -> {
            products.add(product);
        });
    }
}
