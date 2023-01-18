package com.spring.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //le indico que sera un controlador
@RequestMapping("/administrador")//le indico la url por la cual mostrara mi arhivo html
public class AdministradorController {
	@GetMapping("")//mapeo la pagina de destino
 public String home() {
	 return "administrador/home";//indico la carpeta en la que esta/lanzo mi pagina deseada
 }
}
