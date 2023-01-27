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


/**
 * Controlador para los productos
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);// variable para prueba


	@Autowired
	private ProductoService productoService;

	@Autowired
	private UploadFileService upload;

	@Autowired
	private IUsuarioService usuarioService;

	/**
	 * Metodo para mostrar todos los productos
	 */
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "productos/show";
	}

	/**
	 * Metodo para el recidreccionamiento de la creacion del producto
	 */
	@GetMapping("create")
	public String create() {
		return "productos/create";// le digo que me redireccione a mi pagina create
	}

	/**
	 * METODO PARA TESTEAR DE QUE LOS DATOS SE ESTA GUARDANDO
	 */
	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		LOGGER.info("este es el objeto de producto {}", producto);// (asegurarnos de que tenga el metodo to string)
		//Usuario u = new Usuario(1, "", "", "", "", "", "", "");
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

	/**
	 * Metodo para actualizar el producto
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id);
		producto = optionalProducto.get();
		LOGGER.info("producto Buscado:{}", producto);
		model.addAttribute("producto", producto);

		return "productos/edit";
	}

	@PostMapping("/update")
/** Metodo para actualizar la imagen del producto*/
	public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
		Producto p = new Producto();
		p = productoService.get(producto.getId()).get();

		if (file.isEmpty()) {// cuando editamos un producto pero no cambiamos la imagen
			producto.setImagen(p.getImagen());
		} else {

			if (!p.getImagen().equals("default.jpg")) {
				upload.deleteImage(p.getImagen());
			}

			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		producto.setUsuario(p.getUsuario());
		productoService.update(producto);
		return "redirect:/productos";
	}

	/**
	 * Metodo para elminar el producto
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Producto p = new Producto();

		p = productoService.get(id).get();
		if (!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}
		productoService.delete(id);

		return "redirect:/productos";
	}
}
