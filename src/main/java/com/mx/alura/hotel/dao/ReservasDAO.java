package com.mx.alura.hotel.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.mx.alura.hotel.modelo.Huespedes;
import com.mx.alura.hotel.modelo.Reservas;

public class ReservasDAO {
     
	private EntityManager em;
	
	public ReservasDAO(EntityManager em) {
		   this.em = em;
	}

	public void guardar(Reservas reserva) {
		this.em.persist(reserva);
		
	}
	
	public void actualizar(Reservas reserva) {
		this.em.merge(reserva);
	}
	
	public void eliminar(Reservas reserva) {
		  reserva = this.em.merge(reserva);
		  this.em.remove(reserva);
	 }
	
	public Reservas consultaPorId(Long id) {
		return em.find(Reservas.class, id);
	}
	
	public List<Reservas> consultarTodos() {
		   String jpql = "SELECT R FROM Reservas AS R";
		   return em.createQuery(jpql, Reservas.class).getResultList();
	 }
	
	 
}
