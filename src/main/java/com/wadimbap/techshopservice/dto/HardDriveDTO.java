package com.wadimbap.techshopservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HardDriveDTO extends BaseProductDTO {

    @NotBlank(message = "Form factor cannot be blank")
    @Positive(message = "Capacity must be positive")
    private Integer capacity;
}