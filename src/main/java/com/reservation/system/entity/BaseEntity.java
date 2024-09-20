package com.reservation.system.entity;

import java.sql.Timestamp;


public class BaseEntity {
	
	/** Constructor por omision */
    public BaseEntity() {
        super();
    }

	/**
	 * Asigna la fecha de modificación a este objeto.
	 *
	 * @param fechaModifica Fecha de modificación de este objeto.
	 */
	public void setFechaModifica(Timestamp fechaModifica) {
	}

	/**
	 * Le asigna la fecha de creación a este objeto.
	 *
	 * @param fechaCrea Fecha de creación de este objeto.
	 */
	public void setFechaCrea(Timestamp fechaCrea) {
	}

	/**
	 * Asigna el usuario de creación a este objeto.
	 *
	 * @param usuarioCrea Usuario de creación de este objeto.
	 */
	public void setUsuarioCrea(String usuarioCrea) {
	}

	/**
	 * Asigna el usuario de modificación a este objeto.
	 *
	 * @param usuarioModifica Usuario de modificación de este objeto.
	 */
	public void setUsuarioModifica(String usuarioModifica) {
	}

}
