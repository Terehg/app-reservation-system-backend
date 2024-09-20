package com.reservation.system.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reservation.system.entity.ReservationEntity;
import com.reservation.system.entity.ServiceEntity;

import lombok.Data;

@Data
public class ReservationDto {
	
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO", timezone = "America/Bogota")
    private LocalDate date;
    
	private String name;

    private ServiceDto service;
    
    public ReservationDto convReservationReservationDto(ReservationEntity parReservationEntity, ReservationDto parReservationDto) {
		ReservationDto reservationDto = new ReservationDto();
		BeanUtils.copyProperties(parReservationEntity, reservationDto);
		// Convertir y asignar el ServiceEntity a ServiceDto
	    if (parReservationEntity.getService() != null) {
	        ServiceDto serviceDto = new ServiceDto();
	        BeanUtils.copyProperties(parReservationEntity.getService(), serviceDto);
	        reservationDto.setService(serviceDto);
	    }
		return reservationDto;
	}

    public static ReservationDto convRservationEntityDto(ReservationEntity reservationEntity) {
		ReservationDto reservationDto = new ReservationDto();
		BeanUtils.copyProperties(reservationEntity, reservationDto);
		return reservationDto;
	}
    public static ReservationDto convReservationEntityToDto(ReservationEntity parReservationEntity, ReservationDto parReservationDto) {
    	ReservationDto reservationDto = new ReservationDto();
    	BeanUtils.copyProperties(parReservationEntity,parReservationDto);
    	return reservationDto;
    }
    
    public static ReservationEntity convReservationDtoToEntity(ReservationDto parReservationDto) {
    	ReservationEntity reservationEntity = new ReservationEntity();
    	BeanUtils.copyProperties(parReservationDto, reservationEntity);
    	// Asignar los IDs de customer y service
        reservationEntity.setService(new ServiceEntity());
        reservationEntity.getService().setId(parReservationDto.getService().getId());
    	return reservationEntity;
    }
    
    public static ReservationDto convReservationEntityDto(ReservationEntity reservationEntity) {
        if (reservationEntity == null) {
            return null;
        }
        ReservationDto reservationDto = new ReservationDto();
        BeanUtils.copyProperties(reservationEntity, reservationDto);
        
        // Convertir y asignar el ServiceEntity a ServiceDto
        if (reservationEntity.getService() != null) {
            ServiceDto serviceDto = new ServiceDto();
            BeanUtils.copyProperties(reservationEntity.getService(), serviceDto);
            reservationDto.setService(serviceDto);
        }
        
        return reservationDto;
    }

}
