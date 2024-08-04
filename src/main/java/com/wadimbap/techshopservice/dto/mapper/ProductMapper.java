package com.wadimbap.techshopservice.dto.mapper;

import com.wadimbap.techshopservice.dto.*;
import com.wadimbap.techshopservice.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id")
    DesktopComputer toDesktopComputer(DesktopComputerDTO dto);

    @Mapping(target = "id")
    Laptop toLaptop(LaptopDTO dto);

    @Mapping(target = "id")
    Monitor toMonitor(MonitorDTO dto);

    @Mapping(target = "id")
    HardDrive toHardDrive(HardDriveDTO dto);

    @Mapping(target = "id")
    DesktopComputerDTO toDesktopComputerDTO(DesktopComputer entity);

    @Mapping(target = "id")
    LaptopDTO toLaptopDTO(Laptop entity);

    @Mapping(target = "id")
    MonitorDTO toMonitorDTO(Monitor entity);

    @Mapping(target = "id")
    HardDriveDTO toHardDriveDTO(HardDrive entity);
}