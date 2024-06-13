package co.edu.co.jan.diego.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import co.edu.co.jan.diego.model.Product;
import co.edu.co.jan.diego.model.Usuario;
import co.edu.co.jan.diego.service.UploadFileService;
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

import co.edu.co.jan.diego.service.IUserService;
import co.edu.co.jan.diego.service.ProductService;

@Controller
@RequestMapping("/productos")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private IUserService usuarioService;

    @Autowired
    private UploadFileService upload;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", productService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Product product, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        LOGGER.info("Este es el objeto producto {}", product);

        Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        product.setUsuario(u);
        //imagen
        if (product.getId() == null) { // cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            product.setImagen(nombreImagen);
        } else {

        }
        productService.save(product);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = new Product();
        Optional<Product> optionalProducto = productService.get(id);
        product = optionalProducto.get();

        LOGGER.info("Producto buscado: {}", product);
        model.addAttribute("producto", product);

        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Product product, @RequestParam("img") MultipartFile file) throws IOException {
        Product p = new Product();
        p = productService.get(product.getId()).get();

        if (file.isEmpty()) { // editamos el producto pero no cambiamos la imagem

            product.setImagen(p.getImagen());
        } else {// cuando se edita tbn la imagen
            //eliminar cuando no sea la imagen por defecto
            if (!p.getImagen().equals("default.jpg")) {
                upload.deleteImage(p.getImagen());
            }
            String nombreImagen = upload.saveImage(file);
            product.setImagen(nombreImagen);
        }
        product.setUsuario(p.getUsuario());
        productService.update(product);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Product p = new Product();
        p = productService.get(id).get();

        //eliminar cuando no sea la imagen por defecto
        if (!p.getImagen().equals("default.jpg")) {
            upload.deleteImage(p.getImagen());
        }

        productService.delete(id);
        return "redirect:/productos";
    }


}
