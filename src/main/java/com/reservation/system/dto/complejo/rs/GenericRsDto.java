package com.reservation.system.dto.complejo.rs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericRsDto {
	
	Boolean success;
	String typeError;
	String descriptionError;
		
	@JsonProperty("success")
	public Boolean getSuccess() {
		return success;
	}
	
	@JsonProperty("success")
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@JsonProperty("typeError")
	public String getTypeError() {
		return typeError;
	}
	
	@JsonProperty("typeError")
	public void setTypeError(String typeError) {
		this.typeError = typeError;
	}
	
	@JsonProperty("descriptionError")
	public String getDescriptionError() {
		return descriptionError;
	}
	
	@JsonProperty("descriptionError")
	public void setDescriptionError(String descriptionError) {
		this.descriptionError = descriptionError;
	}
	
	/** Metodo para setear la respuesta del método */
	public GenericRsDto setResponseError (String parTypeError, String parDescriptionError) {
		
			this.setSuccess(Boolean.FALSE);
			this.setTypeError(parTypeError);
			this.setDescriptionError(parDescriptionError);
		
		return this;
	}
	
	/** Metodo para setear la respuesta del método */
	public GenericRsDto setResponse (Boolean parValidationMethod, String parTypeError, String parDescriptionError) {
		
			this.setSuccess(parValidationMethod);
			this.setTypeError(parTypeError);
			this.setDescriptionError(parDescriptionError);
		
		return this;
	}

}
