
package com.reservation.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.system.business.IReservationService;
import com.reservation.system.dto.ReservationDto;
import com.reservation.system.dto.complejo.rq.ReservationRqDto;
import com.reservation.system.dto.complejo.rs.GenericDataRsDto;
import com.reservation.system.dto.complejo.rs.GenericListRsDto;
import com.reservation.system.dto.complejo.rs.GenericRsDto;
import com.reservation.system.utilities.Constants;

@RestController
@CrossOrigin (origins = Constants.ASTERISCO, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(Constants.OPERATIONS_RESERVATION)
public class OperationsReservationController {

	@Autowired
	private IReservationService reservationService;
	
	@PostMapping(value = Constants.CREATE_RESERVATION, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericRsDto> createReservation(@RequestBody ReservationRqDto reservation){
		GenericRsDto responseCreateReservation = reservationService.createReservation(reservation);
		if(responseCreateReservation.getTypeError() != null) {
			return new ResponseEntity<>(responseCreateReservation, HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return new ResponseEntity<>(responseCreateReservation, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = Constants.GET_RESERVATION_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericListRsDto<ReservationDto>> gettReservationAll() {
		GenericListRsDto<ReservationDto> responseConsultAllReservation = reservationService.getReservationAll();
		if (responseConsultAllReservation.getTypeError() != null) {
			return new ResponseEntity<>(responseConsultAllReservation, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			if (responseConsultAllReservation.getListData().isEmpty()) {
				responseConsultAllReservation.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_WARNING,
						Constants.CONSTANT_MESSAGE_THERE_IS_NOT_REGISTERS);
				return new ResponseEntity<>(responseConsultAllReservation, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(responseConsultAllReservation, HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = Constants.DELETE_RESERVATION, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericRsDto> deleteReservation(@RequestBody ReservationDto reservationDto){
		GenericRsDto responseDeleteReservation = reservationService.deleteReservation(reservationDto);
		if(responseDeleteReservation.getTypeError()!= null) {
			return new ResponseEntity<>(responseDeleteReservation, HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return new ResponseEntity<>(responseDeleteReservation, HttpStatus.OK);
		}
	}
	@PutMapping(value = Constants.UPDATE_RESERVATION, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericRsDto> updateReservation (@RequestBody ReservationDto reservationDto) {
		GenericRsDto responseUpdateReservation = reservationService.updateReservation(reservationDto);
		if (responseUpdateReservation.getTypeError() != null) {
			return new ResponseEntity<>(responseUpdateReservation, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(responseUpdateReservation, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/get-reservation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericDataRsDto<ReservationDto>> getReservationById(@PathVariable Long id) {
		GenericDataRsDto<ReservationDto> response = reservationService.getReservationById(id);
	    if (response.getTypeError() != null) {
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    } else {
	        if (response.getDataGeneric() == null) {
	            response.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_WARNING, "Reservation not found");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	}
}


