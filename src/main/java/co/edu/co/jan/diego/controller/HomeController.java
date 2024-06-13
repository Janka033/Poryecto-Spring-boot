package co.edu.co.jan.diego.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import co.edu.co.jan.diego.dtos.OrderDetailsDto;
import co.edu.co.jan.diego.dtos.ProductDto;
import co.edu.co.jan.diego.mapping.UserMapper;
import co.edu.co.jan.diego.model.Order;
import co.edu.co.jan.diego.model.OrderDetails;
import co.edu.co.jan.diego.model.Product;
import co.edu.co.jan.diego.model.Usuario;
import co.edu.co.jan.diego.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.co.jan.diego.service.IDetalleOrdenService;
import co.edu.co.jan.diego.service.IOrderService;
import co.edu.co.jan.diego.service.IUserService;

@Controller
@RequestMapping("/")
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductService productService;
	
	@Autowired
	private IUserService usuarioService;
	
	
	@Autowired
	private IOrderService ordenService;
	
	@Autowired
	private IDetalleOrdenService detalleOrdenService;

	// para almacenar los detalles de la orden
	List<OrderDetails> detalles = new ArrayList<>();

	// datos de la orden
	Order order = new Order();

	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
		
		model.addAttribute("products", productService.findAll());
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "usuario/home";
	}

	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parámetro {}", id);
		Product product = new Product();
		Optional<Product> productoOptional = productService.get(id);
		product = productoOptional.get();

		model.addAttribute("product", product);

		return "usuario/productohome";
	}

	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		OrderDetails orderDetails = new OrderDetails();
		Product product = new Product();
		double sumaTotal = 0;

		Optional<Product> optionalProducto = productService.get(id);
		log.info("Producto añadido: {}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);
		product = optionalProducto.get();

		orderDetails.setCantidad(cantidad);
		orderDetails.setPrecio(product.getPrecio());
		orderDetails.setNombre(product.getNombre());
		orderDetails.setTotal(product.getPrecio() * cantidad);
		orderDetails.setProduct(product);
		
		//validar que le producto no se añada 2 veces
		Integer idProducto= product.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getId()  == idProducto);

		if (!ingresado) {
			detalles.add(orderDetails);
		}
		
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		order.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", order);

		return "usuario/carrito";
	}

	// quitar un producto del carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {

		// lista nueva de prodcutos
		List<OrderDetails> ordenesNueva = new ArrayList<OrderDetails>();

		for (OrderDetails orderDetails : detalles) {
			if (orderDetails.getProduct().getId() != id) {
				ordenesNueva.add(orderDetails);
			}
		}

		// poner la nueva lista con los productos restantes
		detalles = ordenesNueva;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		order.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", order);

		return "usuario/carrito";
	}
	
	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", order);
		
		//sesion
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "/usuario/carrito";
	}
	
	@GetMapping("/order")
	public String order(Model model, HttpSession session) {
		
		Usuario usuario = usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", order);
		model.addAttribute("usuario", usuario);
		
		return "usuario/resumenorden";
	}
	
	// guardar la orden
	@GetMapping("/saveOrder")
	public String saveOrder(HttpSession session ) {
		Date fechaCreacion = new Date();
		order.setFechaCreacion(fechaCreacion);
		order.setNumero(ordenService.generarNumeroOrden());
		
		//usuario
		Usuario usuario = usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())  ).get();
		
		order.setUsuario(usuario);
		ordenService.save(order);
		
		//guardar detalles
		for (OrderDetails dt:detalles) {
			dt.setOrder(order);
			detalleOrdenService.save(dt);
		}
		
		///limpiar lista y orden
		order = new Order();
		detalles.clear();
		
		return "redirect:/";
	}
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre, Model model) {
		log.info("Nombre del producto: {}", nombre);
		List<ProductDto> products = productService.findAll().stream().filter(p -> p.nombre().contains(nombre)).collect(Collectors.toList());
		model.addAttribute("products", products);
		return "usuario/home";
	}

}
