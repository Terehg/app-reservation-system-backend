package com.reservation.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.system.business.IServiceService;
import com.reservation.system.dto.ServiceDto;
import com.reservation.system.dto.complejo.rq.ServiceRqDto;
import com.reservation.system.dto.complejo.rs.GenericListRsDto;
import com.reservation.system.dto.complejo.rs.GenericRsDto;
import com.reservation.system.utilities.Constants;

@RestController
@CrossOrigin(origins = Constants.ASTERISCO, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(Constants.OPERATIONS_SERVICE)
public class OperationsServiceController {
	
	@Autowired
	private IServiceService serviceService;
	
	@PostMapping(value = Constants.CREATE_SERVICE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericRsDto> createService(@RequestBody ServiceRqDto service) {
		GenericRsDto responseCreateService = serviceService.createService(service);
		if (responseCreateService.getTypeError() != null) {
			return new ResponseEntity<>(responseCreateService, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(responseCreateService, HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = Constants.DELETE_SERVICE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericRsDto> deleteService(@RequestBody ServiceDto serviceDto){
		GenericRsDto responseDeleteService = serviceService.deleteService(serviceDto);
		if(responseDeleteService.getTypeError()!= null) {
			return new ResponseEntity<>(responseDeleteService, HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return new ResponseEntity<>(responseDeleteService, HttpStatus.OK);
		}
	}
	
	@PutMapping(value = Constants.UPDATE_SERVICE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericRsDto> updateService(@RequestBody ServiceDto serviceDto){
		GenericRsDto responseUpdateService = serviceService.updateService(serviceDto);
		if(responseUpdateService.getTypeError() != null) {
			return new ResponseEntity<>(responseUpdateService, HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return new ResponseEntity<>(responseUpdateService, HttpStatus.OK);
		}
	}
	
	@PostMapping(value = Constants.CONSULT_SERVICE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericListRsDto<ServiceDto>> consultService(){
		GenericListRsDto<ServiceDto> responseConsultAllService = serviceService.getService();
		if (responseConsultAllService.getTypeError() != null) {
			return new ResponseEntity<>(responseConsultAllService, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			if (responseConsultAllService.getListData().isEmpty()) {
				responseConsultAllService.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_WARNING,
						Constants.CONSTANT_MESSAGE_THERE_IS_NOT_REGISTERS);
				return new ResponseEntity<>(responseConsultAllService, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(responseConsultAllService, HttpStatus.OK);
		}
	}
}

