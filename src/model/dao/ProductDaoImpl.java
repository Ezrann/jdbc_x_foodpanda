package model.dao;

import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao{

    @Override
    public List<Product> queryAllProduct() {
        String sql = """
                SELECT * FROM "product"
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ){
            List<Product> products = new ArrayList<>();
            while (resultSet.next()){
                products.add(
                        new Product(
                                resultSet.getInt("id"),
                                resultSet.getString("product_name"),
                                resultSet.getString("product_code"),
                                resultSet.getBoolean("is_deleted"),
                                resultSet.getDate("imported_at"),
                                resultSet.getDate("expired_at"),
                                resultSet.getString("product_description")
                        )
                );
            }
            return products;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int updateProductById(Integer id) {
        String sql = """
                UPDATE "product" SET product_name = ?, product_code = ?  
                WHERE id = ?;
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            Product products = searchProductById(id);
            if(products != null){
                System.out.print("[+] Insert product name: ");
                preparedStatement.setString(1, new Scanner(System.in).next());
                System.out.print("[+] Insert product code: ");
                preparedStatement.setString(2, new Scanner(System.in).next());
                preparedStatement.setInt(3, id);
                int rows = preparedStatement.executeUpdate();
                String message = rows>0 ? "Update Successfully" : "Update Faild";
                System.out.println(message);
            }else {
                System.out.println("Product not found");
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteProductById(Integer id) {
        String sql = """
                DELETE FROM "product" 
                WHERE id = ?
                """;
        String sql1 = """
                DELETE FROM "product_order" 
                WHERE pro_id = ?
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        ){
            Product products = searchProductById(id);
            if(products == null) {
                System.out.print("Cannot Delete");
            }else{
                preparedStatement.setInt(1, id);
                preparedStatement1.setInt(1, id);
                preparedStatement1.executeUpdate();
                int rows = preparedStatement.executeUpdate();
                if(rows > 0) {
                    System.out.println("Deleted Successfully");
                }
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int insertProduct(Product product) {
        String sql = """
                INSERT INTO "product" (product_name, product_code, is_deleted, imported_at, expired_at, product_description) 
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductCode());
            preparedStatement.setBoolean(3, product.getIsDeleted());
            preparedStatement.setDate(4, product.getImportedDate());
            preparedStatement.setDate(5, product.getExpiredDate());
            preparedStatement.setString(6, product.getProductDescription());
            int rows = preparedStatement.executeUpdate();
            if(rows > 0) {
                System.out.println("Insert Successfully");
            }

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public Product searchProductById(Integer id) {
        String sql = """
                SELECT * FROM "product" 
                WHERE id = ?
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Product products = null;
            while(resultSet.next()) {
                products = products.builder()
                        .id(resultSet.getInt("id"))
                        .productName(resultSet.getString("product_name"))
                        .productCode(resultSet.getString("product_code"))
                        .isDeleted(resultSet.getBoolean("is_deleted"))
                        .importedDate(resultSet.getDate("imported_at"))
                        .expiredDate(resultSet.getDate("expired_at"))
                        .productDescription(resultSet.getString("product_description"))
                        .build();
            }
            return products;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }
}
