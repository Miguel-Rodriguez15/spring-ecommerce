package com.spring.ecommerce.service;

import com.spring.ecommerce.model.DetalleOrden;

/**
 * Clase para los metodos cruds de los detalles de la orden
 */
public interface IDetalleOrdenService {
    /**
     * Metodo para guardar el detalle de la orden
     */
    DetalleOrden save(DetalleOrden detalleOrden);
}
