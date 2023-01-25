package com.spring.ecommerce.controller;

import java.util.List;

import com.spring.ecommerce.service.IOrdenService;
import com.spring.ecommerce.service.IUsuarioService;
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

    @Autowired
    private IOrdenService ordenService;
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("") // mapeo la pagina de destino
    public String home(Model model) {

        List<Producto> productos = productoService.findAll();//lista de tipo productos y todos los prodctos de la base de datos
        model.addAttribute("productos", productos);//lo paso a la vista home
        return "administrador/home";// indico la carpeta en la que esta/lanzo mi pagina deseada
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        //obtenemos todos los usuarios y los pasamos atreves de una lista
        model.addAttribute("usuarios", usuarioService.findall());
        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", ordenService.findAll());
        return "administrador/ordenes";
    }

}
