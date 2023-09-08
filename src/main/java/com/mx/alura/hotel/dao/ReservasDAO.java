package com.mx.alura.hotel.dao;

import javax.persistence.EntityManager;

import com.mx.alura.hotel.modelo.Reservas;

public class ReservasDAO {
     
	private EntityManager em;
	
	public ReservasDAO(EntityManager em) {
		   this.em = em;
	}

	public void guardar(Reservas reserva) {
		em.persist(reserva);
		
	}
	
	public Reservas consultaPorId(Reservas id) {
		return em.find(Reservas.class, id);
	}
	
}
