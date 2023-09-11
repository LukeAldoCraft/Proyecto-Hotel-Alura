package com.mx.alura.hotel.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.mx.alura.hotel.modelo.Huespedes;
import com.mx.alura.hotel.modelo.Reservas;


public class HuespedesDAO {

	
	 private EntityManager em;
	 
	 public HuespedesDAO(EntityManager em) {
		 this.em = em;
	 }
	 
	 public void guardar(Huespedes huesped) {
			this.em.persist(huesped);
			
		}
	 
	 public void actualizar(Huespedes huesped) {
			this.em.merge(huesped);
		}
	 
	 
	 public void eliminar(Huespedes huesped) {
		  huesped = this.em.merge(huesped);
		  this.em.remove(huesped);
	 }
		
		
		public Huespedes consultaPorId(Long id) {
			return em.find(Huespedes.class, id);
		}
		
	 public List<Huespedes> consultarTodos() {
		   String jpql = "SELECT H FROM Huespedes AS H";
		   return em.createQuery(jpql, Huespedes.class).getResultList();
	 }
	 
	 public List<Huespedes> consultarPorApellido(String apellido) {
			String jpql = "SELECT H FROM Huespedes AS H WHERE H.apellido=:apellido";
			return em.createQuery(jpql, Huespedes.class).setParameter("apellido", apellido).getResultList();
		}
	 
	 
	 public Huespedes consultarPorIdReserva(Long id) {
		   String jpql = "SELECT H FROM Huespedes AS H WHERE H.reserva.id=:id";
		   return em.createQuery(jpql, Huespedes.class).setParameter("id", id).getSingleResult();
	 }
}
