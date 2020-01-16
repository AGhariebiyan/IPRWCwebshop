package nl.alirezaa.resources;

import java.sql.SQLException;
import java.util.List;

import nl.alirezaa.authorization.JWTUtils;
import nl.alirezaa.model.ProductModel;
import nl.alirezaa.services.ProductService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/product")
public class ProductResource {
    private ProductService productService;

    public ProductResource(){
        this.productService = new ProductService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductModel> getAllProducts(@HeaderParam("jwt-token") String token) throws SQLException, ClassNotFoundException{
        return productService.getAllProducts();
    }

    @RolesAllowed("ADMIN")
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProduct(ProductModel product, @HeaderParam("jwt-token") String token) throws SQLException, ClassNotFoundException{
        productService.addProduct(product);
    }

    @RolesAllowed("ADMIN")
    @Path("/delete/{id}")
    @DELETE
    public void deleteProduct(@PathParam("id") int id, @HeaderParam("jwt-token") String token) throws SQLException, ClassNotFoundException{
        if (JWTUtils.getInstance().verifyJwtToken(token)) {
            productService.deleteProduct(id, token);
        }

    }

    @RolesAllowed("ADMIN")
    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProductFromId(@PathParam("id") int id, ProductModel product, @HeaderParam("jwt-token") String token) throws SQLException, ClassNotFoundException{
        productService.updateProductFromId(id, product);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProductModel getProductById(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
        return productService.getProductById(id);
    }

}
