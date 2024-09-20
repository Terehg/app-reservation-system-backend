package com.reservation.system.business;

import com.reservation.system.dto.ReservationDto;
import com.reservation.system.dto.complejo.rq.ReservationRqDto;
import com.reservation.system.dto.complejo.rs.GenericDataRsDto;
import com.reservation.system.dto.complejo.rs.GenericListRsDto;
import com.reservation.system.dto.complejo.rs.GenericRsDto;

public interface IReservationService {
	
	/**
	 * Método para crear una nueva reserva en el sistema.
	 * @param reservation: Objeto de solicitud para crear una reserva
	 * @return un objeto true o false
	 * @author tguarnizo
	 */
	public GenericRsDto createReservation(ReservationRqDto reservation);
	
	public GenericListRsDto<ReservationDto> getReservationAll();
	
	public GenericRsDto deleteReservation(ReservationDto reservation);
	
	public GenericRsDto  updateReservation(ReservationDto reservation);
	
	public GenericDataRsDto<ReservationDto> getReservationById(Long id);
}
