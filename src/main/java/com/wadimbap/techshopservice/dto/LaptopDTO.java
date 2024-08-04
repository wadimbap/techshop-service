package com.wadimbap.techshopservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LaptopDTO extends BaseProductDTO {

    @NotBlank(message = "Screen size cannot be blank")
    @Positive(message = "Screen size must be positive")
    private Integer screenSize;
}