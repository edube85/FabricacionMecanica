package com.edusoft.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edusoft.entity.Role;
import com.edusoft.entity.Usuario;
import com.edusoft.models.service.IPerfilesService;
import com.edusoft.models.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Value("${spring.data.web.pageable.default-page-size}")
	private int numero;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuariosService serviceUsuarios;
	
	@Autowired
	private IPerfilesService servicePerfiles;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "usuarios/formRegistro";
	}
	
	@PostMapping("/signup")
	public String guardarRegistro (Usuario usuario, RedirectAttributes attributes, Locale locale) {
		
		try {
		String pwdPlano = usuario.getPassword();
		String pwdEncriptado = passwordEncoder.encode(pwdPlano);
		usuario.setPassword(pwdEncriptado);
		usuario.setEnabled(true);
		usuario.setAuthority("USUARIO");
		serviceUsuarios.guardar(usuario);
		
		
		Role perfil = new Role();
		perfil.setAuthority("ROLE_USER");
		perfil.setUser(usuario);
		
		servicePerfiles.guardar(perfil);
		}catch(Exception ex) {
			attributes.addFlashAttribute("error", messageSource.getMessage("text.registro.error",null,locale));
			return "redirect:/usuarios/signup";
		}
		String mensaje = String.format(messageSource.getMessage("text.registro.success", null,locale),usuario.getUsername());
		attributes.addFlashAttribute("success",mensaje);
		
		return "redirect:/login";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value ="/listUsuarios", method = RequestMethod.GET)
	public String listar(Model model) {
    	List<Usuario> lista = serviceUsuarios.buscarTodos();
    	model.addAttribute("usuarios", lista);
		return "usuarios/listUsuarios";

	}
	
	@RequestMapping(value ="delete/{id}")
	public String eliminar(@PathVariable ("id") Long id) {
		
		serviceUsuarios.eliminar(id);
		
		return "redirect:/usuarios/listUsuarios";
	}
	
	@RequestMapping(value ="lock/{id}")
	public String bloquear(@PathVariable("id") long idUsuario) {
		serviceUsuarios.bloquear(idUsuario);
		
		return "redirect:/usuarios/listUsuarios";
	}
	
	@RequestMapping(value ="unlock/{id}")
	public String desbloquear(@PathVariable("id") long idUsuario) {
		serviceUsuarios.activar(idUsuario);
		
		return "redirect:/usuarios/listUsuarios";
	}
	
	@RequestMapping(value ="permisos/{id}")
	public String permisos(@PathVariable("id") long idUsuario) {
		serviceUsuarios.permisos(idUsuario);
		serviceUsuarios.cambioRole(idUsuario);
		return "redirect:/usuarios/listUsuarios";
	}
	
	@RequestMapping(value ="quitarpermisos/{id}")
	public String quitarPermisos(@PathVariable("id") long idUsuario) {
		serviceUsuarios.quitarPermisos(idUsuario);
		serviceUsuarios.roleUsuario(idUsuario);
		return "redirect:/usuarios/listUsuarios";
	}

}
