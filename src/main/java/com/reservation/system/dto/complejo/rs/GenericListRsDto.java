package com.reservation.system.dto.complejo.rs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericListRsDto<T> extends GenericRsDto {
	
	private List<T> listData;

	@JsonProperty("listData")
	public List<T> getListData() {
		return listData;
	}

	@JsonProperty("listData")
	public void setListData(List<T> listData) {
		this.listData = listData;
	}
	
}
