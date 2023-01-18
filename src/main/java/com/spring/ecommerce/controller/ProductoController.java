package com.spring.ecommerce.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ecommerce.model.Producto;
import com.spring.ecommerce.model.Usuario;
import com.spring.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);//variable para prueba
    
	@Autowired
	private ProductoService productoService;
	
	
	@GetMapping("")
	public String show() {
	 return "productos/show";
 }
      @GetMapping("create")
      public String create() {
    	  return "productos/create";//le digo que me redireccione a mi pagina create
      }
      //METODO PARA TESTEAR DE QUE LOS DATOS SE ESTA GUARDANDO
      @PostMapping("/save")
      public String save(Producto producto) {
    	  LOGGER.info("este es el objeto de producto {}",producto);//(asegurarnos de que tenga el metodo to string)
    	Usuario u = new Usuario(1, "", "", "", "", "", "", "");
    	producto.setUsuario(u);
    	  productoService.save(producto);
    	  return "redirect:/productos";
      }
}
