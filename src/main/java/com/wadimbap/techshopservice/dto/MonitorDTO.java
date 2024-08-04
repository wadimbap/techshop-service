package com.wadimbap.techshopservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MonitorDTO extends BaseProductDTO {

    @NotBlank(message = "Diagonal cannot be blank")
    @Positive(message = "Diagonal must be positive.")
    private Double diagonal;
}