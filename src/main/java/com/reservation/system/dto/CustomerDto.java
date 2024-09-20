package com.reservation.system.dto;

import org.springframework.beans.BeanUtils;

import com.reservation.system.entity.CustomerEntity;

import lombok.Data;

@Data
public class CustomerDto {
	
	private Long id;
	
    private String name;
    
    private String email;
    
    
    public CustomerDto convCustomerCustomerDto(CustomerEntity parCustomerEntity, CustomerDto parCustomerDto) {
		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(parCustomerEntity, customerDto);
		return customerDto;
	}

    public static CustomerDto convCustomerEntityDto(CustomerEntity customerEntity) {
		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(customerEntity, customerDto);
		return customerDto;
	}
    public static CustomerDto convCustomerEntityToDto(CustomerEntity parCustomerEntity, CustomerDto parCustomerDto) {
    	CustomerDto customerDto = new CustomerDto();
    	BeanUtils.copyProperties(parCustomerEntity, parCustomerDto);
    	return customerDto;
    }
    
    public static CustomerEntity convCustomerDtoToEntity(CustomerDto parCustomerDto) {
    	CustomerEntity customerEntity = new CustomerEntity();
    	BeanUtils.copyProperties(parCustomerDto, customerEntity);
    	return customerEntity;
    }
    
}
