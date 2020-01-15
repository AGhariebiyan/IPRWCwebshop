package nl.alirezaa.services;

import nl.alirezaa.DAO.ProductDao;
import nl.alirezaa.authorization.JWTconnection;
import nl.alirezaa.model.ProductModel;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDao productDao;
    private JWTconnection jwtConnection;

    public ProductService(){
        this.productDao = new ProductDao();
    }

    public List<ProductModel> getAllProducts() throws SQLException, ClassNotFoundException {
        return productDao.findAllProducts();
    }

    public void addProduct(ProductModel product) throws SQLException, ClassNotFoundException{
        productDao.addProduct(product);
    }

    public boolean deleteProduct(int id, String token) throws SQLException, ClassNotFoundException{
        if (jwtConnection.verifyJwtToken(token)) {
            productDao.deleteProduct(id);
            return true;
        } else {
            return false;
        }

    }

    public ProductModel getProductById(int id) throws SQLException, ClassNotFoundException{
        return productDao.getProductById(id);
    }

    public void updateProductFromId(int id, ProductModel product) throws SQLException, ClassNotFoundException{
        productDao.updateProductFromId(id, product);
    }
}
