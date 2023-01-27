package com.spring.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import com.spring.ecommerce.model.Usuario;
import com.spring.ecommerce.service.IDetalleOrdenService;
import com.spring.ecommerce.service.IOrdenService;
import com.spring.ecommerce.service.IUsuarioService;
import com.spring.ecommerce.service.ProductoService;

import jakarta.servlet.http.HttpSession;

/**
 * Clase para las sesiones y redireccionamientos de vistas para el del admin y el user
 *
 * @autor Miguel Rodriguez
 */
@Controller
@RequestMapping("/")
public class HomeController {
	private final Logger log = LoggerFactory.getLogger(HomeController.class);


	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	//datos de la orden
	Orden orden = new Orden();

	@Autowired
	private ProductoService productoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IOrdenService ordenService;

	@Autowired
	private IDetalleOrdenService detalleOrdenService;

	/**
	 * Metodo para el manejo de sesiones
	 */
	@GetMapping("")
	public String home(Model model, HttpSession session) {

		log.info("sesion del usuario:{}", session.getAttribute("idusuario"));

		model.addAttribute("productos", productoService.findAll());


		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "usuario/home";
	}

	/**
	 * metodo recuperar el id del producto al usar la opcion (ver el producto)
	 */
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parametro{}", id);
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();
		model.addAttribute("producto", producto);
		return "usuario/productohome";
	}

	/**
	 * Metodo para hacer uso de la opcion del carrito
	 *
	 * @param cantidad , obtenemos la cantidad enviada por la vista y la alamacenamos en nuestra base de datos
	 */
	@PostMapping("/cart")
	public String addcart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;
		Optional<Producto> optionalProducto = productoService.get(id);
		log.info("Producto agregado: {}", optionalProducto.get());
		log.info("cantidad {}", cantidad);
		producto = optionalProducto.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto);


		Integer idProducto = producto.getId();
		boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

		if (!ingresado) {


			detalles.add(detalleOrden);
		}


		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);//agregamos al carrito los detalles
		model.addAttribute("orden", orden);//pasamos por la orden el total

		return "usuario/carrito";
	}

	/**
	 * Meotodo para quitar quitar un producto del carrito
	 *
	 * @param id, el id es la identificacion del detalle de la orden
	 */
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCar(@PathVariable Integer id, Model model) {
		//lista nueva de productos
		List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();

		//si encuientro un id que este en dellaes ese no lo agrega a las ordenes nuevas
		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getProducto().getId() != id) {
				ordenesNuevas.add(detalleOrden);

			}
		}

		detalles = ordenesNuevas;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}

	/**
	 * metodo para ver el carrito desde el header
	 */
	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {


		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);


		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "/usuario/carrito";
	}

	/**
	 * mostrar los datos de la orden y del usuario
	 */
	@GetMapping("/order")
	public String order(Model model, HttpSession session) {
		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);
		return "usuario/resumenorden";
	}

	/**
	 * Metodo para guardar la orden
	 */
	@GetMapping("/saveOrder")
	public String saveOrder(HttpSession session) {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNumeroOrden());

		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

		orden.setUsuario(usuario);
		ordenService.save(orden);

		//guardar detalles

		for (DetalleOrden dt : detalles) {
			dt.setOrden(orden);
			detalleOrdenService.save(dt);
		}

		//limpiar lista y orden
		orden = new Orden();
		detalles.clear();


		return "redirect:/";
	}

	/**
	 * Metodo para buscar los productos
	 *
	 * @param nombre , este objeto hace una comparacion con la lista de productos que estan en la base de datos
	 */
	@PostMapping("/search")

	public String searchProduct(@RequestParam String nombre, Model model) {
		log.info("Nombre del producto:{}", nombre);

		List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
		model.addAttribute("productos", productos);

		return "usuario/home";
	}

}
