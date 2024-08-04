package com.wadimbap.techshopservice.service;

import com.wadimbap.techshopservice.dto.BaseProductDTO;
import com.wadimbap.techshopservice.dto.mapper.ProductMapperService;
import com.wadimbap.techshopservice.model.BaseProduct;
import com.wadimbap.techshopservice.repository.BaseProductRepository;
import com.wadimbap.techshopservice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final BaseProductRepository baseProductRepository;
    private final ProductMapperService productMapperService;

    public BaseProductDTO createProduct(BaseProductDTO productDTO) {
        logger.info("Creating product: {}", productDTO);

        BaseProduct product = productMapperService.mapToEntity(productDTO);
        product = baseProductRepository.save(product);

        logger.info("Product saved: {}", product);

        return productMapperService.mapToDTO(product);
    }

    public BaseProductDTO updateProduct(Long id, BaseProductDTO productDTO) {
        logger.info("Updating product with id {}: {}", id, productDTO);

        BaseProduct updatedProduct = baseProductRepository.findById(id)
                .map(existingProduct -> {
                    BaseProduct product = productMapperService.mapToEntity(productDTO);
                    product.setId(id);
                    return baseProductRepository.save(product);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        logger.info("Product updated: {}", updatedProduct);

        return productMapperService.mapToDTO(updatedProduct);
    }

    public List<BaseProductDTO> getAllProducts() {
        logger.info("Getting all products");

        List<BaseProduct> products = baseProductRepository.findAll();

        logger.info("Number of products retrieved: {}", products.size());

        return products.stream()
                .map(productMapperService::mapToDTO)
                .collect(Collectors.toList());
    }

    public BaseProductDTO getProductById(Long id) {
        logger.info("Getting product with id {}", id);

        BaseProduct product = baseProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        logger.info("Product retrieved: {}", product);

        return productMapperService.mapToDTO(product);
    }
}
