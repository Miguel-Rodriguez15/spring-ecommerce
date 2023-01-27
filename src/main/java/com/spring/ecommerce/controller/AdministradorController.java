package com.spring.ecommerce.controller;

import java.util.List;

import com.spring.ecommerce.model.Orden;
import com.spring.ecommerce.service.IOrdenService;
import com.spring.ecommerce.service.IUsuarioService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ecommerce.model.Producto;
import com.spring.ecommerce.service.ProductoService;

/**
 * Controlador para el administrador
 *
 * @autor Miguel Rodriguez
 */
@Controller // le indico que sera un controlador
@RequestMapping("/administrador") // le indico la url por la cual mostrara mi arhivo html
public class AdministradorController {

    //llamo mi variables con mis servicios (crud)
    @Autowired
    private ProductoService productoService;
    //loger para verficacion de datos por consola
    private Logger logg = LoggerFactory.getLogger(AdministradorController.class);

    //llamado de los servicios de la orden para llamado de cruds
    @Autowired
    private IOrdenService ordenService;

    //llamado de los servicios del usuario para llamado de cruds
    @Autowired
    private IUsuarioService usuarioService;

    /**
     * Metodo para el listado de productos en la home
     * del administrador,
     * se muestra el listado de los productos.
     */
    @GetMapping("")
    public String home(Model model) {

        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "administrador/home";
    }

    /**
     * Metodo para mostrar el listado de los usuarios
     */

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findall());
        return "administrador/usuarios";
    }

    /**
     * Metodo para mostrar el listado de las ordenes
     */
    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", ordenService.findAll());
        return "administrador/ordenes";
    }


    /**
     * Creacion del metodo detalle orden para el administrador
     *
     * @param id, identificador de la orden  que se encuentra en la base de tados
     */
    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        logg.info("id de la orden {}", id);
        Orden orden = ordenService.findById(id).get();
        model.addAttribute("detalles", orden.getDetalle());
        return "administrador/detalleorden";
    }
}
