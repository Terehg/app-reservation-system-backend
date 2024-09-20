package com.reservation.system.dto;

import org.springframework.beans.BeanUtils;


import com.reservation.system.entity.ServiceEntity;

import lombok.Data;

@Data
public class ServiceDto {
	
	private Long id;
	
    private String name;

    
    public ServiceDto convServiceServiceDto(ServiceEntity parServiceEntity, ServiceDto parServiceDto) {
		ServiceDto serviceDto = new ServiceDto();
		BeanUtils.copyProperties(parServiceEntity, serviceDto);
		return serviceDto;
	}

    public static ServiceDto convServiceEntityDto(ServiceEntity serviceEntity) {
    	ServiceDto serviceDto = new ServiceDto();
		BeanUtils.copyProperties(serviceEntity, serviceDto);
		return serviceDto;
	}
    public static ServiceDto convServiceEntityToDto(ServiceEntity parServiceEntity, ServiceDto parServiceDto) {
    	ServiceDto serviceDto = new ServiceDto();
    	BeanUtils.copyProperties(parServiceEntity, parServiceDto);
    	return serviceDto;
    }
    
    public static ServiceEntity convServiceDtoToEntity(ServiceDto parServiceDto) {
    	ServiceEntity serviceEntity = new ServiceEntity();
    	BeanUtils.copyProperties(parServiceDto, serviceEntity);
    	return serviceEntity;
    }
}
