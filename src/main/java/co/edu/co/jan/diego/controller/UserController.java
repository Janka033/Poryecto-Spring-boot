package co.edu.co.jan.diego.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import co.edu.co.jan.diego.dtos.UserDto;
import co.edu.co.jan.diego.mapping.UserMapper;
import co.edu.co.jan.diego.model.Order;
import co.edu.co.jan.diego.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.co.jan.diego.service.IOrderService;
import co.edu.co.jan.diego.service.IUserService;

@Controller
@RequestMapping("/usuario")
public class UserController {
	
	private final Logger logger= LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService usuarioService;
	
	@Autowired
	private IOrderService ordenService;
	
	BCryptPasswordEncoder passEncode= new BCryptPasswordEncoder();
	
	
	// /usuario/registro
	@GetMapping("/registro")
	public String create() {
		return "usuario/registro";
	}
	
	@PostMapping("/save")
	public String save(Usuario usuario) {
		logger.info("Usuario registro: {}", usuario);
		usuario.setTipo("USER");
		usuario.setPassword( passEncode.encode(usuario.getPassword()));
		usuarioService.save(usuario);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}
	
	@PostMapping("/acceder")
	public String acceder(UserDto usuario, HttpSession session) {
		logger.info("Accesos : {}", usuario);
		
		Optional<Usuario> user= usuarioService.findByEmail(usuario.email());
		//logger.info("Usuario de db: {}", user.get());
		
		if (user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());
		}else {
			logger.info("Usuario no existe");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		Usuario usuario = usuarioService.findById(  Integer.parseInt(session.getAttribute("idusuario").toString()) ).get();
		List<Order> ordenes= ordenService.findByUsuario(usuario);
		logger.info("ordenes {}", ordenes);
		
		model.addAttribute("ordenes", ordenes);
		
		return "usuario/compras";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
		logger.info("Id de la orden: {}", id);
		Optional<Order> orden=ordenService.findById(id);
		
		model.addAttribute("detalles", orden.get().getDetalle());
		
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "usuario/detallecompra";
	}
	
	@GetMapping("/cerrar")
	public String cerrarSesion( HttpSession session ) {
		session.removeAttribute("idusuario");
		return "redirect:/";
	}
}
