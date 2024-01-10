package org.aelion.product.product;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;
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
        if(product.getId() == null || product.getId().isEmpty()) throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Product id is null or empty");
        if(product.getStock() <= 0) throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Product stock is null or empty");
        if(product.getLabel() == null || product.getLabel().isEmpty()) throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Product label is null or empty");
        if (products.stream().anyMatch(p -> p.getId().equals(product.getId()))) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Product already exists");
        }
        products.add(product);
    }

    public Product getProductById(String id) {
        var product = products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (product == null) throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Product not found");
        return product;
    }

    public void removeProductById(String id) {
        var product = products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (product == null) throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Product not found");
        products.remove(product);
    }
}
