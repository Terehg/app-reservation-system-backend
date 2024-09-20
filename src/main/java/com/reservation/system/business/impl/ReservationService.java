package com.reservation.system.business.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.system.business.IReservationService;
import com.reservation.system.business.handler.exception.GeneralException;
import com.reservation.system.dto.ReservationDto;
import com.reservation.system.dto.complejo.rq.ReservationRqDto;
import com.reservation.system.dto.complejo.rs.GenericDataRsDto;
import com.reservation.system.dto.complejo.rs.GenericListRsDto;
import com.reservation.system.dto.complejo.rs.GenericRsDto;
import com.reservation.system.entity.ReservationEntity;
import com.reservation.system.entity.ServiceEntity;
import com.reservation.system.repository.IReservationRepository;
import com.reservation.system.repository.IServiceRepository;
import com.reservation.system.utilities.Constants;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	private IReservationRepository reservationRepository;
	@Autowired
	private IServiceRepository serviceRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericRsDto createReservation(ReservationRqDto reservation) {
		GenericRsDto result = new GenericRsDto();
		ReservationEntity reservationEntity = ReservationDto.convReservationDtoToEntity(reservation.getReservation());
		reservationRepository.save(reservationEntity);
		result.setSuccess(Boolean.TRUE);
		return result;
	}

	@Override
	public GenericListRsDto<ReservationDto> getReservationAll() {
		GenericListRsDto<ReservationDto> result = new GenericListRsDto<>();
		try {
			ReservationDto reservationDto = new ReservationDto();
			result.setListData(reservationRepository.findAll().stream()
					.map(item -> reservationDto.convReservationReservationDto(item, reservationDto))
					.collect(Collectors.toList()));
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			result.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_ERROR, Constants.MESSAGE_ERROR);
		}
		return result;
	}

	@Override
	public GenericRsDto deleteReservation(ReservationDto reservation) {
		GenericRsDto result = new GenericRsDto();
		result.setSuccess(Boolean.FALSE);
		try {
			Optional<ReservationEntity> reservationOptional = reservationRepository.findById(reservation.getId());
			if (reservationOptional.isPresent()) {
				reservationRepository.delete(reservationOptional.get());
				result.setSuccess(Boolean.TRUE);
			} else {
				result.setResponseError(Constants.TYPE_MESSAGE_ERROR, Constants.CONSTANT_MESSAGE_ID_CUSTOMER_DONT_EXIT);
			}
		} catch (GeneralException e) {
			result.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_ERROR, Constants.CODIGO_MENSAJE_053);
		}
		return result;
	}

	@Override
	public GenericRsDto updateReservation(ReservationDto reservation) {
		GenericRsDto result = new GenericRsDto();
		result.setSuccess(Boolean.FALSE);
		try {
			if (reservation.getService() == null || reservation.getService().getId() == null) {
				result.setResponseError(Constants.TYPE_MESSAGE_ERROR, Constants.SERVICE_NULL);
				return result;
			}
			Optional<ReservationEntity> reservationOptionalUpdate = reservationRepository.findById(reservation.getId());
			Optional<ServiceEntity> serviceOptionalUpdate = serviceRepository
					.findById(reservation.getService().getId());
			if (reservationOptionalUpdate.isPresent()) {
				reservationOptionalUpdate.get().setName(reservation.getName());
				reservationOptionalUpdate.get().setDate(reservation.getDate());
				reservationOptionalUpdate.get().setService(serviceOptionalUpdate.get());

				reservationRepository.save(reservationOptionalUpdate.get());
				result.setSuccess(Boolean.TRUE);
			} else {
				result.setResponseError(Constants.TYPE_MESSAGE_ERROR, Constants.CONSTANT_MESSAGE_ID_CUSTOMER_DONT_EXIT);
			}

		} catch (GeneralException e) {
			result.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_ERROR, Constants.CODIGO_MENSAJE_053);
		}
		return result;
	}

	@Override
	public GenericDataRsDto<ReservationDto> getReservationById(Long id) {
		GenericDataRsDto<ReservationDto> result = new GenericDataRsDto<>();
	    try {
	        Optional<ReservationEntity> reservationOptional = reservationRepository.findById(id);
	        if (reservationOptional.isPresent()) {
	            ReservationDto reservationDto = ReservationDto.convReservationEntityDto(reservationOptional.get());
	            result.setDataGeneric(reservationDto);
	            result.setSuccess(Boolean.TRUE);
	        } else {
	            result.setResponseError(Constants.TYPE_MESSAGE_ERROR, "Reservation not found");
	        }
	    } catch (Exception e) {
	        result.setResponse(Boolean.FALSE, Constants.TYPE_MESSAGE_ERROR, Constants.MESSAGE_ERROR);
	    }
	    return result;
	}
}
