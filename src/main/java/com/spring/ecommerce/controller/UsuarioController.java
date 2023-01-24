package com.spring.ecommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ecommerce.model.Usuario;
import com.spring.ecommerce.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
     private final Logger logger= LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/registro")
	public String create() {
		return "usuario/registro";
	}
	
	@PostMapping("/save")
	public String save(Usuario usuario) {
		logger.info("Usuario regustro:{}", usuario);
		usuario.setTipo("USER");
		usuarioService.save(usuario);
		return "redirect:/";
	}
	@GetMapping("/login")
	
	public String login() {
		return "usuario/login";
		
	}
	@PostMapping("/acceder")
	public String acceder(Usuario usuario) {
		//verificamos de que los datos estan siendo recibidos y los mostramos por consola
		logger.info("accesos: {}", usuario);
		//obtenemos un usuario por la interfaz y nos devuelbva uno igual a la base datos
		Optional<Usuario> user= usuarioService.findByEmail(usuario.getEmail());
		/*regresamos por consola el registro del usuario con el correo que hemos enviado*/
		logger.info("usuario de db: {}",user.get());
		return "redirect:/";
	}
	
}
