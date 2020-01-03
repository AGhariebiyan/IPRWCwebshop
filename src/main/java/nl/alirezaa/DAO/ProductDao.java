package nl.alirezaa.DAO;

import nl.alirezaa.model.ProductModel;
import nl.alirezaa.dbConnection.dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private ProductModel makeModel(ResultSet result){
        ProductModel product = new ProductModel();
        try {
            product.setId(result.getInt("p_id"));
            product.setName(result.getString("p_name"));
            product.setDescription(result.getString("p_description"));
            product.setPrice(result.getInt("p_price"));
            product.setAmount(result.getInt("p_amount"));
            product.setImage_link(result.getString("p_image_link"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // Ophalen alle producten
    public List<ProductModel> findAllProducts() throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM product";
        PreparedStatement statement = dbConnection.getInstance().createPreparedStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<ProductModel> productList = new ArrayList<>();
        while (resultSet.next()){
            productList.add(makeModel(resultSet));
        }

        return productList;
    }

    public void addProduct(ProductModel product) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO product(p_name, p_description, p_price, p_amount, p_image_link) VALUES (?,?,?,?,?)";
        PreparedStatement statement = dbConnection.getInstance().createPreparedStatement(query);
        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setDouble(3, product.getPrice());
        statement.setInt(4, product.getAmount());
        statement.setString(5, product.getImage_link());

        statement.execute();
    }

    public void deleteProduct(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM product WHERE p_id = ?";
        PreparedStatement statement = dbConnection.getInstance().createPreparedStatement(query);
        statement.setInt(1, id);

        statement.execute();
    }

    public ProductModel getProductById(int id) throws SQLException, ClassNotFoundException {
        ProductModel product;

        String query = "SELECT * FROM product WHERE p_id = ?";
        PreparedStatement statement = dbConnection.getInstance().createPreparedStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            product = makeModel(resultSet);
            return product;
        }

        return null;
    }

    public void updateProductFromId(int id, ProductModel product) throws SQLException, ClassNotFoundException {
        String query = "UPDATE product SET " +
                "p_name = ?, " +
                "p_description = ?, " +
                "p_price = ?, " +
                "p_amount = ?, " +
                "p_image_link = ? " +
                "WHERE p_id = ? ";
        PreparedStatement statement = dbConnection.getInstance().createPreparedStatement(query);
        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setDouble(3, product.getPrice());
        statement.setInt(4, product.getAmount());
        statement.setString(5, product.getImage_link());
        statement.setInt(6, id);

        statement.execute();
    }
}
