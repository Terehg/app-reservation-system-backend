package com.reservation.system.business;

import com.reservation.system.dto.ServiceDto;
import com.reservation.system.dto.complejo.rq.ServiceRqDto;
import com.reservation.system.dto.complejo.rs.GenericListRsDto;
import com.reservation.system.dto.complejo.rs.GenericRsDto;

public interface IServiceService {
 
	public GenericRsDto createService(ServiceRqDto service);
	
	public GenericRsDto deleteService(ServiceDto service);
	
	public GenericRsDto updateService(ServiceDto service);
	
	public GenericListRsDto<ServiceDto> getService();
}
