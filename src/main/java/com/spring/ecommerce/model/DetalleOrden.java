package com.spring.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Clase detalles de la orden
 *
 * @Miguel Rodriguez
 */
@Entity
@Table(name = "detalles")
public class DetalleOrden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String nombre;
	private double cantidad;
	private double precio;
	private double total;

	@ManyToOne
	private Orden orden;

	@ManyToOne
	private Producto producto;

	public DetalleOrden() {

	}

	/***
	 *Constructor detalles de la orden
	 * @param id   , identificador de la orden
	 * @param nombre , nombre del producto en la orden
	 * @param cantidad , cantidad de productos
	 * @param precio , precio del producto
	 * @param total , cantidad total de la orden
	 */

	public DetalleOrden(Integer id, String nombre, double cantidad, double precio, double total) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * metodo para devolver el nombre del producto en la orden
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * metodo para modificar el nombre del producto en la orden
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * metodo para mostrar la cantidad de cada producto en la orden
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * metodo para modificar la cantidad de cada producto en la orden
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * metodo para mostrar el precio del producto
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * metodo para modificar el precio del producto
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * metodo para mostrar el total de la orden
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * metodo para modificar el total de la orden
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * metodo para mostrar toda la orden
	 */
	public Orden getOrden() {
		return orden;
	}

	/**
	 * metodo para modificar toda la orden
	 */
	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	/**
	 * metodo para mostrar el producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * metodo para modificar el producto
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	@Override
	public String toString() {
		return "DetalleOrden [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
				+ ", total=" + total + "]";
	}


}
