package com.reservation.system.business;

import com.reservation.system.dto.CustomerDto;
import com.reservation.system.dto.complejo.rq.CustomerRqDto;
import com.reservation.system.dto.complejo.rs.GenericListRsDto;
import com.reservation.system.dto.complejo.rs.GenericRsDto;

public interface ICustomerService {
	
	public GenericRsDto createCustomer(CustomerRqDto customer);
	
	public GenericListRsDto<CustomerDto> getCustomer();
	
	public GenericRsDto  updateCustomer(CustomerDto customer);
	
	public GenericRsDto deleteCustomer(CustomerDto customer);
}
