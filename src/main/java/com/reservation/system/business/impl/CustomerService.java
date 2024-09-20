package com.reservation.system.business.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.system.business.ICustomerService;
import com.reservation.system.business.handler.exception.GeneralException;
import com.reservation.system.dto.CustomerDto;
import com.reservation.system.dto.complejo.rq.CustomerRqDto;
import com.reservation.system.dto.complejo.rs.GenericListRsDto;
import com.reservation.system.dto.complejo.rs.GenericRsDto;
import com.reservation.system.entity.CustomerEntity;
import com.reservation.system.repository.ICustomerRepository;
import com.reservation.system.utilities.Constants;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public GenericRsDto createCustomer(CustomerRqDto customer) {
		GenericRsDto response = new GenericRsDto();
		//instancia clase y agrega nuevo valor, convierttiendo los datos obtenido en customer y convertirlo en objeto de tipo entity.
		CustomerEntity customerEntity = CustomerDto.convCustomerDtoToEntity(customer.getCustomer());
		customerRepository.save(customerEntity);
		response.setSuccess(Boolean.TRUE);
		return response;
	}

	@Override
	public GenericListRsDto<CustomerDto> getCustomer() {
		GenericListRsDto<CustomerDto> result = new GenericListRsDto<>();
		try {
			CustomerDto customerDto = new CustomerDto();
			result.setListData(customerRepository.findAll().stream()
					.map(item -> customerDto.convCustomerCustomerDto(item, customerDto)).collect(Collectors.toList()));
		} catch (Exception e) {
			result.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_ERROR, Constants.MESSAGE_ERROR);
		}
		return result;
	}

	@Override
	public GenericRsDto updateCustomer(CustomerDto customer) {
		GenericRsDto result = new GenericRsDto();
		result.setSuccess(Boolean.FALSE);
		try {
			Optional<CustomerEntity> customerOptionalUpdate = customerRepository.findById(customer.getId());
			if(customerOptionalUpdate.isPresent()) {
				customerOptionalUpdate.get().setName(customer.getName());
				customerOptionalUpdate.get().setEmail(customer.getEmail());
				customerRepository.save(customerOptionalUpdate.get());
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
	public GenericRsDto deleteCustomer(CustomerDto customer) {
		GenericRsDto result = new GenericRsDto();
		result.setSuccess(Boolean.FALSE);
		try {
			
			Optional<CustomerEntity> customerOptional = customerRepository.findById(customer.getId());
			if(customerOptional.isPresent()) {
				/**/
				customerRepository.delete(customerOptional.get());
				result.setSuccess(Boolean.TRUE);
			}else {
				result.setResponseError(Constants.TYPE_MESSAGE_ERROR,
						Constants.CONSTANT_MESSAGE_ID_CUSTOMER_DONT_EXIT);
			}
		} catch (GeneralException e) {
			result.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_ERROR, Constants.CODIGO_MENSAJE_053);
		}
		return result;
	}
}
