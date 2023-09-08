package com.mx.alura.hotel.modelo;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reservas {
     
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date fechaDeEntrada;
	
	@Temporal(TemporalType.DATE)
	private Date fechaDeSalida;
	private BigDecimal valor;
	private String formaDePago;
	
	
	public Reservas() {
		
	}


	public Reservas(Date fechaDeEntrada, Date fechaDeSalida, BigDecimal valor, String formaDePago) {
		
		this.fechaDeEntrada = fechaDeEntrada;
		this.fechaDeSalida = fechaDeSalida;
		this.valor = valor;
		this.formaDePago = formaDePago;
	}


	public Long getId() {
		return id;
	}


	public Date getFechaDeEntrada() {
		return fechaDeEntrada;
	}


	public void setFechaDeEntrada(Date fechaDeEntrada) {
		this.fechaDeEntrada = fechaDeEntrada;
	}


	public Date getFechaDeSalida() {
		return fechaDeSalida;
	}


	public void setFechaDeSalida(Date fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public String getFormaDePago() {
		return formaDePago;
	}


	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	
	
}
