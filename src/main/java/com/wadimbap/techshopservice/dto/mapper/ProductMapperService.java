package com.wadimbap.techshopservice.dto.mapper;

import com.wadimbap.techshopservice.dto.*;
import com.wadimbap.techshopservice.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapperService {

    private final ProductMapper productMapper;

    public BaseProduct mapToEntity(BaseProductDTO dto) {
        return switch (dto) {
            case DesktopComputerDTO desktopDTO -> productMapper.toDesktopComputer(desktopDTO);
            case LaptopDTO laptopDTO -> productMapper.toLaptop(laptopDTO);
            case MonitorDTO monitorDTO -> productMapper.toMonitor(monitorDTO);
            case HardDriveDTO hardDriveDTO -> productMapper.toHardDrive(hardDriveDTO);
            default -> throw new IllegalArgumentException("Unknown DTO type: " + dto.getClass().getSimpleName());
        };
    }

    public BaseProductDTO mapToDTO(BaseProduct entity) {
        return switch (entity) {
            case DesktopComputer desktop -> productMapper.toDesktopComputerDTO(desktop);
            case Laptop laptop -> productMapper.toLaptopDTO(laptop);
            case Monitor monitor -> productMapper.toMonitorDTO(monitor);
            case HardDrive hardDrive -> productMapper.toHardDriveDTO(hardDrive);
            default -> throw new IllegalArgumentException("Unknown entity type: " + entity.getClass().getSimpleName());
        };
    }
}