package com.wadimbap.techshopservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "productType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DesktopComputerDTO.class, name = "DESKTOP"),
        @JsonSubTypes.Type(value = LaptopDTO.class, name = "LAPTOP"),
        @JsonSubTypes.Type(value = MonitorDTO.class, name = "MONITOR"),
        @JsonSubTypes.Type(value = HardDriveDTO.class, name = "HARD_DRIVE")
})
@Data
public abstract class BaseProductDTO {

    private Long id;

    @NotBlank(message = "Serial number cannot be blank")
    private String serialNumber;

    @NotBlank(message = "Manufacturer cannot be blank")
    private String manufacturer;

    @Positive(message = "Price must be positive")
    private Double price;

    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @JsonIgnore
    private String productType;
}
