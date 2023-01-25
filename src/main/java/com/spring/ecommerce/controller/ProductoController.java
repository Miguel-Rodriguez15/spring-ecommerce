package com.spring.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ecommerce.model.Producto;
import com.spring.ecommerce.model.Usuario;
import com.spring.ecommerce.service.IUsuarioService;
import com.spring.ecommerce.service.ProductoService;
import com.spring.ecommerce.service.UploadFileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);// variable para prueba

	// creamos un objeto para poder acceder a los metodos crud de la clase
	@Autowired
	private ProductoService productoService;

	@Autowired
	private UploadFileService upload;

	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("")
	public String show(Model model) {// este parametro es un objeto envia la lista de objeto producto hacia la vista
										// show
		model.addAttribute("productos", productoService.findAll());
		return "productos/show";
	}

	@GetMapping("create")
	public String create() {
		return "productos/create";// le digo que me redireccione a mi pagina create
	}

	// METODO PARA TESTEAR DE QUE LOS DATOS SE ESTA GUARDANDO
	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		LOGGER.info("este es el objeto de producto {}", producto);// (asegurarnos de que tenga el metodo to string)
		//Usuario u = new Usuario(1, "", "", "", "", "", "", "");// llamo mi entidad usuario y sus respectivos atributos
		Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get(); //le envio el usuario con el id 1 para testeo ya que toda orden necedita un usuario
														// para enviar los datos por parametros
		producto.setUsuario(u);// almaceno los parametros enviados anteriormente

		// imagen
		if (producto.getId() == null) {// cuando se crea un nuevo producto
			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		} else {

		}

		productoService.save(producto);// guardo mis parametros en la base de datos
		return "redirect:/productos";
	}

	@GetMapping("/edit/{id}") // buscamos el id del registro en la variabel{id}
	public String edit(@PathVariable Integer id, Model model) {// llamo mi objeyo model
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id);// devolvemos el id
		producto = optionalProducto.get();// traemos el pdroducto que solicitamos buscar
		LOGGER.info("producto Buscado:{}", producto);// mostramos el id solicitado por consola
		model.addAttribute("producto", producto);// mi objeto model agrego los atributos le paso el valor producto para
													// cargar en la vista
		return "productos/edit";// retornamos la vista
	}

	@PostMapping("/update")

	public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {// le envio por
		// producto
		Producto p = new Producto();
		p = productoService.get(producto.getId()).get();

		if (file.isEmpty()) {// cuando editamos un producto pero no cambiamos la imagen
			producto.setImagen(p.getImagen());
		} else {// cuando se edita la imagen
			// eliminar cuando no sea la imagen por defecto
			if (!p.getImagen().equals("default.jpg")) {
				upload.deleteImage(p.getImagen());
			}

			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		producto.setUsuario(p.getUsuario());
		productoService.update(producto);// llamo mi creud update le envio por paramtero mi objeto
		return "redirect:/productos";// redireccion a mi listado de productos
	}

	@GetMapping("/delete/{id}") // buscamos el id del registro en la variabel{id}
	public String delete(@PathVariable Integer id) {// alamaceno en mi nuevo objeto id
		Producto p = new Producto();
		// eliminar cuando no sea la imagen por defecto
		p = productoService.get(id).get();
		if (!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}
		productoService.delete(id);// llamo mi crud y elimino el id enviado por parametro

		return "redirect:/productos";
	}
}
