package service;

import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    int deleteProduct(Integer id);
    int updateProduct(Integer id);
    int addProduct(Product product);
}
