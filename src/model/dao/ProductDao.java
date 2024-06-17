package model.dao;

import model.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> queryAllProduct();
    int updateProductById(Integer id);
    int deleteProductById(Integer id);
    int insertProduct(Product product);
    Product searchProductById(Integer id);
}
