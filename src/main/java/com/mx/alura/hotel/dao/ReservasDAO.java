package com.mx.alura.hotel.dao;

import javax.persistence.EntityManager;

public class ReservasDAO {
     
	private EntityManager em;
	
	public ReservasDAO(EntityManager em) {
		   this.em = em;
	}
	
}
