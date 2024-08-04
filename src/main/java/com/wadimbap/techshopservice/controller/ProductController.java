package com.wadimbap.techshopservice.controller;

import com.wadimbap.techshopservice.dto.BaseProductDTO;
import com.wadimbap.techshopservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody BaseProductDTO productDTO) {
        var product = productService.createProduct(productDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
                                           @Valid @RequestBody BaseProductDTO productDTO) {
        var product = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<BaseProductDTO>> getAllProducts() {
        var product = productService.getAllProducts();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseProductDTO> getProductById(@PathVariable Long id) {
        var product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent()
                .build();
    }
}