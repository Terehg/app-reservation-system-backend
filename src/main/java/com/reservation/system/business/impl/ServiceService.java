package com.reservation.system.business.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.system.business.IServiceService;
import com.reservation.system.business.handler.exception.GeneralException;

import com.reservation.system.dto.ServiceDto;
import com.reservation.system.dto.complejo.rq.ServiceRqDto;
import com.reservation.system.dto.complejo.rs.GenericListRsDto;
import com.reservation.system.dto.complejo.rs.GenericRsDto;
import com.reservation.system.entity.ServiceEntity;
import com.reservation.system.repository.IServiceRepository;
import com.reservation.system.utilities.Constants;

@Service
public class ServiceService implements IServiceService {

	@Autowired
	private IServiceRepository serviceRepository;
	
	@Override
	public GenericRsDto createService(ServiceRqDto service) {
		GenericRsDto response = new GenericRsDto();
		ServiceEntity serviceEntity = ServiceDto.convServiceDtoToEntity(service.getService());
		serviceRepository.save(serviceEntity);
		response.setSuccess(Boolean.TRUE);
		return response;
	}

	@Override
	public GenericRsDto deleteService(ServiceDto service) {
		GenericRsDto result = new GenericRsDto();
		result.setSuccess(Boolean.FALSE);
		try {
			Optional<ServiceEntity> serviceOptional = serviceRepository.findById(service.getId());
			if(serviceOptional.isPresent()){
				serviceRepository.delete(serviceOptional.get());
				result.setSuccess(Boolean.TRUE);
			}else {
				result.setResponseError(Constants.TYPE_MESSAGE_ERROR,Constants.CONSTANT_MESSAGE_ID_CUSTOMER_DONT_EXIT);
			}
		}catch(GeneralException e) {
			result.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_ERROR, Constants.CODIGO_MENSAJE_053);
		}
		return result;
	}

	
	@Override
	public GenericRsDto updateService(ServiceDto service) {
		GenericRsDto result = new GenericRsDto();
		result.setSuccess(Boolean.FALSE);
		try {
			Optional<ServiceEntity> serviceOptionalUpdate = serviceRepository.findById(service.getId());
			if (serviceOptionalUpdate.isPresent()) {
				serviceOptionalUpdate.get().setName(service.getName());
				serviceRepository.save(serviceOptionalUpdate.get());
				result.setSuccess(Boolean.TRUE);
			}else {
				result.setResponseError(Constants.TYPE_MESSAGE_ERROR,
						Constants.CONSTANT_MESSAGE_ID_CUSTOMER_DONT_EXIT);
			}
		}catch (GeneralException e){
			result.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_ERROR, Constants.CODIGO_MENSAJE_053);
		}
		
		return result;
	}

	@Override
	public GenericListRsDto<ServiceDto> getService() {
		GenericListRsDto<ServiceDto> result = new GenericListRsDto<>();
		try {
			ServiceDto serviceDto = new ServiceDto();
			result.setListData(serviceRepository.findAll().stream()
					.map(item -> serviceDto.convServiceServiceDto(item, serviceDto)).collect(Collectors.toList()));
		}catch(Exception e) {
			result.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_ERROR, Constants.MESSAGE_ERROR);
		}
		return result;
	}


	
}

