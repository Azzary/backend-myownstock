package org.aelion.product.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    @GetMapping("/product")
    public @ResponseBody Product getProduct(@Validated @RequestBody String id) {
        return service.getProductById(id);
    }

    //remove product by id
    @DeleteMapping
    public @ResponseBody void removeProduct(@Validated @RequestBody String id) {
        service.removeProductById(id);
    }

    @PutMapping
    public void add(@Validated @RequestBody Product product) {
        service.AddProduct(product);
    }


}