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
		
		
		public Huespedes consultaPorId(Long id) {
			return em.find(Huespedes.class, id);
		}
		
	 public List<Huespedes> consultarTodos() {
		   String jpql = "SELECT H FROM Huespedes AS H";
		   return em.createQuery(jpql, Huespedes.class).getResultList();
	 }
	 
	 public List<Huespedes> consultarPorNombre(String apellido) {
			String jpql = "SELECT R FROM Huespedes AS R WHERE R.apellido=:apellido";
			return em.createQuery(jpql, Huespedes.class).setParameter("apellido", apellido).getResultList();
		}
}
