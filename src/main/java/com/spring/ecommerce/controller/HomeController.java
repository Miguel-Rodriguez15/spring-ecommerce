package com.spring.ecommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ecommerce.model.Producto;
import com.spring.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/")//le indico que se lanzara desde la ruta raiz
public class HomeController {
	private final Logger log=LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private ProductoService productoService;//lamo mis ervicios

	@GetMapping("")
	public String home(Model model) {//llamo miclase model para poder llamar los atributos
		model.addAttribute("productos", productoService.findAll());//traigo todos los atributos de productos
		return "usuario/home";
	}
	//metodo recuperar el id del producto al usar la opcion (ver el producto)
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {//llamo mi clase model para traer los atributos del productp
		log.info("Id producto enviado como parametro{}",id);//le envio por consola el id del producto
		Producto producto = new Producto();//llamo mi objeto de tipo producto
		Optional<Producto> productoOptional = productoService.get(id);//almaceno mi servicio get id producto en mi objeto opcional 
		producto = productoOptional.get();//devuelvo lo almacenado anteriormente almacenandolo en una nueva variabe
		model.addAttribute("producto", producto);//llamo mi clase model y agrego los traiburos del producto	  
	    return "usuario/productohome";
	}
	@PostMapping("/cart")
	public String addcart() {
		return "usuario/carrito";
	}
	
}
