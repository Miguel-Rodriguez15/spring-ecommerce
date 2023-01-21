package com.spring.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.ecommerce.model.DetalleOrden;
import com.spring.ecommerce.model.Orden;
import com.spring.ecommerce.model.Producto;
import com.spring.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/")//le indico que se lanzara desde la ruta raiz
public class HomeController {
	private final Logger log=LoggerFactory.getLogger(HomeController.class);
	
	//para almacenar los detalles de la orden
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
	
	//datos de la orden
	Orden orden = new Orden(); 
	
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
	@PostMapping("/cart")//se hace una peticion de tipo post que redireccione a este metodo
	public String addcart(@RequestParam Integer id, @RequestParam Integer cantidad,Model model ) {
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;
		Optional<Producto> optionalProducto = productoService.get(id);
		log.info("Producto agregado: {}", optionalProducto.get());
		log.info("cantidad {}",cantidad);
		producto=optionalProducto.get();//muestro los productos obtenidos desde la base datos
		
		detalleOrden.setCantidad(cantidad);//lo obtenemos desde la variable cantidad que se envia desde la vista
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio()*cantidad);//multiplicamos el precio del producto por la cantidad para mostrar el total
		detalleOrden.setProducto(producto);
		
		//validar que el produto no se añada dos veces
		Integer idProducto=producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
		
		if(!ingresado) {
		//agregamos todos los atributos almacenados a detalles
		
			detalles.add(detalleOrden);
		}
		
	
		
		//sumamos todo los totales del producto que se encuentren en la lista
		sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);//agregamos al carrito los detalles
		model.addAttribute("orden", orden);//pasamos por la orden el total
		 
		return "usuario/carrito";
	}
	
//quitar un producto del carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCar(@PathVariable Integer id, Model model) {
		//lista nueva de productos
		List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();
		
		//si encuientro un id que este en dellaes ese no lo agrega a las ordenes nuevas
		for(DetalleOrden detalleOrden:detalles) {
			if(detalleOrden.getProducto().getId()!=id) {
				ordenesNuevas.add(detalleOrden);
				
			}
		}
		//poner la nueva lista con los productos restantes
		detalles=ordenesNuevas;
		
		double sumaTotal=0;
		sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		 
		return "usuario/carrito";
	}
	
}
