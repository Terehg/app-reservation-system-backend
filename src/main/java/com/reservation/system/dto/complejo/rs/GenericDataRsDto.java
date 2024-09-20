package com.reservation.system.dto.complejo.rs;

public class GenericDataRsDto<T> extends GenericRsDto {
	
	private T data;

	public T getDataGeneric() {
		return data;
	}

	public void setDataGeneric(T dataGenric) {
		this.data = dataGenric;
	}
	
}
