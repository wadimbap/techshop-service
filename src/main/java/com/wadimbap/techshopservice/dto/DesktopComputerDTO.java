package com.wadimbap.techshopservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DesktopComputerDTO extends BaseProductDTO {
    @NotBlank(message = "Form factor cannot be blank")
    private String formFactor;
}