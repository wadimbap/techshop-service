package com.wadimbap.techshopservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wadimbap.techshopservice.dto.BaseProductDTO;
import com.wadimbap.techshopservice.dto.DesktopComputerDTO;
import com.wadimbap.techshopservice.exception.ResourceNotFoundException;
import com.wadimbap.techshopservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct_Success() throws Exception {
        DesktopComputerDTO productDTO = new DesktopComputerDTO();
        productDTO.setSerialNumber("12345");
        productDTO.setManufacturer("Test Manufacturer");
        productDTO.setPrice(100.0);
        productDTO.setQuantity(10);
        productDTO.setFormFactor("ATX");

        when(productService.createProduct(any(BaseProductDTO.class))).thenReturn(productDTO);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNumber").value("12345"))
                .andExpect(jsonPath("$.manufacturer").value("Test Manufacturer"));
    }

    @Test
    void updateProduct_Success() throws Exception {
        DesktopComputerDTO productDTO = new DesktopComputerDTO();
        productDTO.setSerialNumber("12345");
        productDTO.setManufacturer("Test Manufacturer");
        productDTO.setPrice(100.0);
        productDTO.setQuantity(10);
        productDTO.setFormFactor("ATX");

        when(productService.updateProduct(anyLong(), any(BaseProductDTO.class))).thenReturn(productDTO);

        mockMvc.perform(put("/api/v1/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value("12345"))
                .andExpect(jsonPath("$.manufacturer").value("Test Manufacturer"));
    }

    @Test
    void getAllProducts_Success() throws Exception {
        DesktopComputerDTO productDTO = new DesktopComputerDTO();
        productDTO.setSerialNumber("12345");
        productDTO.setManufacturer("Test Manufacturer");
        productDTO.setPrice(100.0);
        productDTO.setQuantity(10);
        productDTO.setFormFactor("ATX");

        List<BaseProductDTO> products = Collections.singletonList(productDTO);
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].serialNumber").value("12345"))
                .andExpect(jsonPath("$[0].manufacturer").value("Test Manufacturer"));
    }

    @Test
    void getProductById_Success() throws Exception {
        DesktopComputerDTO productDTO = new DesktopComputerDTO();
        productDTO.setSerialNumber("12345");
        productDTO.setManufacturer("Test Manufacturer");
        productDTO.setPrice(100.0);
        productDTO.setQuantity(10);
        productDTO.setFormFactor("ATX");

        when(productService.getProductById(anyLong())).thenReturn(productDTO);

        mockMvc.perform(get("/api/v1/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value("12345"))
                .andExpect(jsonPath("$.manufacturer").value("Test Manufacturer"));
    }

    @Test
    void updateProduct_ProductNotFound() throws Exception {
        DesktopComputerDTO productDTO = new DesktopComputerDTO();
        productDTO.setSerialNumber("12345");
        productDTO.setManufacturer("Test Manufacturer");
        productDTO.setPrice(100.0);
        productDTO.setQuantity(10);
        productDTO.setFormFactor("ATX");

        when(productService.updateProduct(anyLong(), any(BaseProductDTO.class)))
                .thenThrow(new ResourceNotFoundException("Product not found"));

        mockMvc.perform(put("/api/v1/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProductById_ProductNotFound() throws Exception {
        when(productService.getProductById(anyLong()))
                .thenThrow(new ResourceNotFoundException("Product not found"));

        mockMvc.perform(get("/api/v1/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
