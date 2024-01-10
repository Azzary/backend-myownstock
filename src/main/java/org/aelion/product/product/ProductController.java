package org.aelion.product.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductServiceImpl service;

    @GetMapping()
    public List<Product> getProducts() {
        return service.findAll();
    }

    // get product by id
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return service.findAll().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    //remove product by id
    @DeleteMapping("/{id}")
    public void removeProduct(@PathVariable String id) {
        service.findAll().stream().filter(p -> p.getId().equals(id)).findFirst().ifPresent(p -> {
            service.findAll().remove(p);
        });
    }

    @PutMapping
    public @ResponseBody ResponseEntity add(Product product) {
        if(product.getStock() < 0) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock cannot be negative");
        if (product.getId() == null || product.getId().isEmpty()) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id cannot be empty");
        if (product.getLabel() == null || product.getLabel().isEmpty()) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Label cannot be empty");
        service.AddProduct(product);
        // return status 201
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


}