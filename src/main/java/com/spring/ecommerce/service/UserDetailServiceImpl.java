package com.spring.ecommerce.service;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl {
    /*
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    HttpSession session;

    private Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Este es el username");
        Optional<Usuario> optionalUser = usuarioService.findByEmail(username);
        if (optionalUser.isPresent()) {
            log.info("Esto es el id del usuario: {}", optionalUser.get().getId());
            session.setAttribute("idusuario", optionalUser.get().getId());
            Usuario usuario = optionalUser.get();
            return User.builder().username(usuario.getNombre()).password(bCrypt.encode(usuario.getPassword())).roles(usuario.getTipo()).build();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }*/
}

