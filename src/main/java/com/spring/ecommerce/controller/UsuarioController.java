package com.spring.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import com.spring.ecommerce.model.Orden;
import com.spring.ecommerce.service.IOrdenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ecommerce.model.Usuario;
import com.spring.ecommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

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

    /**
     * Metodo para iniciar sesion por el login
     */
    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {

        logger.info("accesos: {}", usuario);

        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());


        if (user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")) {
                return "redirect:/administrador";
            } else {


                return "redirect:/";
            }
        } else {
            logger.info("usuario no existe");
        }


        return "redirect:/";

    }

    /**
     * Metodo para mostrar la lista de compras del usuario
     */
    @GetMapping("/compras")
    public String ObtenerCompras(Model model, HttpSession session) {
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        model.addAttribute("ordenes", ordenes);

        return "usuario/compras";
    }

    /**
     * Metodo para mostrar el detalle de la compra
     */
    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession sessionion, Model model) {
        //testeo para saber el id de la orden por consola
        logger.info("id de la orden: {}", id);

        Optional<Orden> orden = ordenService.findById(id);

        model.addAttribute("detalles", orden.get().getDetalle());
        //sesion
        model.addAttribute("sesion", sessionion.getAttribute("idusuario"));

        return "usuario/detallecompra";
    }

    /**
     * Metodo para cerrar la sesion
     */
    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("idusuario");
        return "redirect:/";
    }
}
