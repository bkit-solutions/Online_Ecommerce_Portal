package Com.smarttrends.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Com.smarttrends.Dtos.ProductDTO;
import Com.smarttrends.Entity.Product;
import Com.smarttrends.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.addProduct(productDTO);
        return ResponseEntity.ok(product);
    }
}

