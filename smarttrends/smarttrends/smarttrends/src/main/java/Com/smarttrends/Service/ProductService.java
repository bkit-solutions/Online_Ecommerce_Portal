package Com.smarttrends.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.smarttrends.Dtos.ProductDTO;
import Com.smarttrends.Entity.Product;
import Com.smarttrends.Repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(ProductDTO dto) {
        Product product = new Product(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getCategory(), dto.getImageUrl());
        return productRepository.save(product);
    }
}
