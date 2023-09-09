package com.mx.alura.hotel.dao;

import javax.persistence.EntityManager;

import com.mx.alura.hotel.modelo.Huespedes;


public class HuespedesDAO {

	
	 private EntityManager em;
	 
	 public HuespedesDAO(EntityManager em) {
		 this.em = em;
	 }
	 
	 public void guardar(Huespedes huesped) {
			em.persist(huesped);
			
		}
		
		public Huespedes consultaPorId(Huespedes id) {
			return em.find(Huespedes.class, id);
		}
		
	 
}
