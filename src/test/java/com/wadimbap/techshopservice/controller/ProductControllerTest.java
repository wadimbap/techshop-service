package com.wadimbap.techshopservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wadimbap.techshopservice.controller.handler.GlobalExceptionHandler;
import com.wadimbap.techshopservice.dto.BaseProductDTO;
import com.wadimbap.techshopservice.dto.DesktopComputerDTO;
import com.wadimbap.techshopservice.exception.ResourceNotFoundException;
import com.wadimbap.techshopservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("POST /api/v1/products создает продукт и возвращает HTTP-ответ со статусом 201 Created")
    void createProduct_Success() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        var productDTO = new DesktopComputerDTO();
        productDTO.setSerialNumber("12345");
        productDTO.setManufacturer("Test Manufacturer");
        productDTO.setPrice(100.0);
        productDTO.setQuantity(10);
        productDTO.setFormFactor("ATX");

        when(productService.createProduct(any(BaseProductDTO.class))).thenReturn(productDTO);

        var result = mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNumber").value("12345"))
                .andExpect(jsonPath("$.manufacturer").value("Test Manufacturer"))
                .andReturn();

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
    }

    @Test
    @DisplayName("PUT /api/v1/products/{id} обновляет продукт и возвращает HTTP-ответ со статусом 200 OK")
    void updateProduct_Success() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        var productDTO = new DesktopComputerDTO();
        productDTO.setSerialNumber("12345");
        productDTO.setManufacturer("Test Manufacturer");
        productDTO.setPrice(100.0);
        productDTO.setQuantity(10);
        productDTO.setFormFactor("ATX");

        when(productService.updateProduct(anyLong(), any(BaseProductDTO.class))).thenReturn(productDTO);

        var result = mockMvc.perform(put("/api/v1/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value("12345"))
                .andExpect(jsonPath("$.manufacturer").value("Test Manufacturer"))
                .andReturn();

        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
    }

    @Test
    @DisplayName("GET /api/v1/products возвращает HTTP-ответ со статусом 200 OK и списком продуктов")
    void getAllProducts_Success() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        var productDTO = new DesktopComputerDTO();
        productDTO.setSerialNumber("12345");
        productDTO.setManufacturer("Test Manufacturer");
        productDTO.setPrice(100.0);
        productDTO.setQuantity(10);
        productDTO.setFormFactor("ATX");

        List<BaseProductDTO> products = Collections.singletonList(productDTO);
        when(productService.getAllProducts()).thenReturn(products);

        var result = mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].serialNumber").value("12345"))
                .andExpect(jsonPath("$[0].manufacturer").value("Test Manufacturer"))
                .andReturn();

        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
    }

    @Test
    @DisplayName("GET /api/v1/products/{id} возвращает HTTP-ответ со статусом 200 OK и продуктом")
    void getProductById_Success() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        var productDTO = new DesktopComputerDTO();
        productDTO.setSerialNumber("12345");
        productDTO.setManufacturer("Test Manufacturer");
        productDTO.setPrice(100.0);
        productDTO.setQuantity(10);
        productDTO.setFormFactor("ATX");

        when(productService.getProductById(anyLong())).thenReturn(productDTO);

        var result = mockMvc.perform(get("/api/v1/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value("12345"))
                .andExpect(jsonPath("$.manufacturer").value("Test Manufacturer"))
                .andReturn();

        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
    }

    @Test
    @DisplayName("PUT /api/v1/products/{id} возвращает HTTP-ответ со статусом 404 Not Found, если продукт не найден")
    void updateProduct_ProductNotFound() throws Exception {
        var productDTO = new DesktopComputerDTO();
        productDTO.setSerialNumber("12345");
        productDTO.setManufacturer("Test Manufacturer");
        productDTO.setPrice(100.0);
        productDTO.setQuantity(10);
        productDTO.setFormFactor("ATX");

        when(productService.updateProduct(anyLong(), any(BaseProductDTO.class)))
                .thenThrow(new ResourceNotFoundException("Product not found"));

        var result = mockMvc.perform(put("/api/v1/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Product not found"))
                .andReturn();

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
    }

    @Test
    @DisplayName("GET /api/v1/products/{id} возвращает HTTP-ответ со статусом 404 Not Found, если продукт не найден")
    void getProductById_ProductNotFound() throws Exception {
        when(productService.getProductById(anyLong()))
                .thenThrow(new ResourceNotFoundException("Product not found"));

        var result = mockMvc.perform(get("/api/v1/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Product not found"))
                .andReturn();

        assertNotNull(result);
        assertEquals(404, result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
    }

    @Test
    @DisplayName("DELETE /api/v1/products/{id} удаляет продукт и возвращает HTTP-ответ со статусом 204 No Content")
    void deleteProductById_Success() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        doNothing().when(productService).deleteProduct(anyLong());

        var result = mockMvc.perform(delete("/api/v1/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
    }

    @Test
    @DisplayName("DELETE /api/v1/products/{id} удаляет продукт и возвращает HTTP-ответ со статусом 204 No Content")
    void deleteProductById_ProductNotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Product not found"))
                .when(productService).deleteProduct(anyLong());

        var result = mockMvc.perform(delete("/api/v1/products/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Product not found"))
                .andReturn();

        assertNotNull(result);
        assertEquals(404, result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
    }
}