package co.edu.co.jan.diego.controller;

import java.util.List;

import co.edu.co.jan.diego.dtos.ProductDto;
import co.edu.co.jan.diego.model.Product;
import co.edu.co.jan.diego.service.IOrderService;
import co.edu.co.jan.diego.service.IUserService;
import co.edu.co.jan.diego.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.co.jan.diego.model.Order;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private IUserService usuarioService;
	
	@Autowired
	private IOrderService ordensService;
	
	private Logger logg= LoggerFactory.getLogger(AdministradorController.class);

	@GetMapping("")
	public String home(Model model) {

		List<ProductDto> products =productService.findAll();
		model.addAttribute("productos", products);


		return "administrador/home";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		return "administrador/usuarios";
	}
	
	@GetMapping("/ordenes")
	public String ordenes(Model model) {
		model.addAttribute("ordenes", ordensService.findAll());
		return "administrador/ordenes";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		logg.info("Id de la orden {}",id);
		Order order = ordensService.findById(id).get();
		
		model.addAttribute("detalles", order.getDetalle());
		
		return "administrador/detalleorden";
	}
	
	
}
