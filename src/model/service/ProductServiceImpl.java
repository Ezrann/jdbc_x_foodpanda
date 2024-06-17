package model.service;

import mapper.Mapper;
import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService{
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<ProductDto> getAllProducts() { return productDao.queryAllProduct().stream().filter(p -> p.getIsDeleted().equals(false)).map(Mapper::fromProductToProductDto).toList(); }

    @Override
    public int deleteProduct(Integer id) { return productDao.deleteProductById(id); }

    @Override
    public int updateProduct(Integer id) { return productDao.updateProductById(id); }

    @Override
    public int addProduct(Product product) { return productDao.insertProduct(product); }
}
