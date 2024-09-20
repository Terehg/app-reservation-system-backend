package com.reservation.system.utilities;

public final class Constants {
	
	/**
	 * Constructora por omisión; privada para evitar instanciaciones.
	 */
	private Constants() {
		super();
	}
	
	public static final String ASTERISCO = "*";
	
	public static final String OPERATIONS_CUSTOMER = "/operations-customer";
	
	public static final String CREATE_CUSTOMER =  "/create-customer";
	
	public static final String  CONSULT_CUSTOMER = "/consult-customer";
	
	public static final String UPDATE_CUSTOMER = "/update-customer";
	
	public static final String DELETE_CUSTOMER = "/delete-customer";
	
	
	public static final String OPERATIONS_SERVICE = "/operations-service";
	
	public static final String CREATE_SERVICE = "/create-service";
	
	public static final String DELETE_SERVICE= "/delete-service";
	
	public static final String UPDATE_SERVICE= "/update-service";
	
	public static final String CONSULT_SERVICE = "/consult-service";
	
	
	public static final String OPERATIONS_RESERVATION = "/operations-reservation";
	
	public static final String CREATE_RESERVATION = "/create-reservation";
	
	public static final String DELETE_RESERVATION= "/delete-reservation";
	
	public static final String UPDATE_RESERVATION= "/update-reservation";
	
	public static final String GET_RESERVATION_ALL = "/get-reservation-all";
	
	
    public static final String CODIGO_MENSAJE_080 = "080";
	
    public static final String CODIGO_MENSAJE_054 = "054";
    
    public static final String CODIGO_MENSAJE_053 = "053";
	

	/** Tipo mensaje Error. */
	public static final String TYPE_MESSAGE_ERROR = "ERR";
	
	/** mensaje Error. */
	public static final String MESSAGE_ERROR = "ERROR";
	
	public static final String SERVICE_NULL = "THE SERVICE CAN'T BE NULL";
	
	/** Tipo mensaje Advertencia. */
	public static final String TYPE_MESSAGE_WARNING = "ADV";
	
	public static final String CONSTANT_MESSAGE_THERE_IS_NOT_REGISTERS = "No hay registros";
	
	public static final String CONSTANT_MESSAGE_ID_CUSTOMER_DONT_EXIT = "El id del usuario no existe";

	
}
