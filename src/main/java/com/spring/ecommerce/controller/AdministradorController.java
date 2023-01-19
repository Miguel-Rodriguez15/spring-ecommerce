package com.spring.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ecommerce.model.Producto;
import com.spring.ecommerce.service.ProductoService;

@Controller // le indico que sera un controlador
@RequestMapping("/administrador") // le indico la url por la cual mostrara mi arhivo html
public class AdministradorController {
	@Autowired
	private ProductoService productoService;//llamo mi variables con mis servicios (crud)

	@GetMapping("") // mapeo la pagina de destino
	public String home(Model model) {

		List<Producto> productos = productoService.findAll();//lista de tipo productos y todos los prodctos de la base de datos
		model.addAttribute("productos", productos);//lo paso a la vista home
		return "administrador/home";// indico la carpeta en la que esta/lanzo mi pagina deseada
	}

}
