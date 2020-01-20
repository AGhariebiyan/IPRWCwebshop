package nl.alirezaa.services;

import nl.alirezaa.DAO.ProductDao;
import nl.alirezaa.model.ProductModel;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService(){
        this.productDao = new ProductDao();
    }

    public List<ProductModel> getAllProducts() throws SQLException, ClassNotFoundException {
        return productDao.findAllProducts();
    }

    public void addProduct(ProductModel product) throws SQLException, ClassNotFoundException{
        productDao.addProduct(product);
    }

    public void deleteProduct(int id) throws SQLException, ClassNotFoundException{
            productDao.deleteProduct(id);
    }

    public ProductModel getProductById(int id) throws SQLException, ClassNotFoundException{
        return productDao.getProductById(id);
    }

    public void updateProductFromId(int id, ProductModel product) throws SQLException, ClassNotFoundException{
        productDao.updateProductFromId(id, product);
    }
}
