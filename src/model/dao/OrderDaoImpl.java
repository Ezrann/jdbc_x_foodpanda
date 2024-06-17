package model.dao;

import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDaoImpl implements OrderDao{

    @Override
    public int addNewOrder(Order order) {
        String sql = """
                INSERT INTO "order" (order_name, order_description, cus_id, ordered_at)
                VALUES (?, ?, ?, ?)
                """;
        String sql1 = """
                   INSERT INTO "product_order"
                   VALUES (?,?)

                   """;
        try(
                Connection connection = DriverManager.getConnection(

                                "jdbc:postgresql://localhost:5432/postgres",
                                "postgres",
                                "Bunrong1211"
                );
                PreparedStatement preparedStatement
                        = connection.prepareStatement(sql);

                PreparedStatement preparedStatement1
                        = connection.prepareStatement(sql1);

                Statement statement
                        = connection.createStatement();

                ){



            preparedStatement.setString(1, order.getOrderName());
            preparedStatement.setString(2,order.getOrderDescription());
            preparedStatement.setInt(3,order.getCustomer().getId());
            preparedStatement.setDate(4,order.getOrderedAt());
            int rowsAffected = preparedStatement.executeUpdate();
            String message = rowsAffected > 0 ? "Order added successfully" : "Order added failed";
            System.out.println(message);
//Product Order
            for (Product product: order.getProductList()){
                preparedStatement1.setInt(1,product.getId());
                preparedStatement1.setInt(2,order.getId());
            }
            int rowAffected1 = preparedStatement1.executeUpdate();
            if (rowAffected1 > 0){
                System.out.println(" Product has been order");
            }else {
                System.out.println(" Product out of stock");
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return 0;
    }

    @Override
    public int deleteOrderById(Integer id) {
        String sql = """
                DELETE FROM "order"
                WHERE id = ?
                """;

        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Bunrong1211"
                );
                PreparedStatement preparedStatement
                        = connection.prepareStatement(sql)


        ){
            Order order = searchOrderById(id);
            if (order!= null){
                preparedStatement.setInt(1,id);
                int rowsAffected = preparedStatement.executeUpdate();
                String message = rowsAffected > 0 ? "Deleted Successfully" : "Deleted failed";
                System.out.println(message);


            }else {
                System.out.println("Not Found order");
            }

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateOrderById(Integer id) {
        String sql = """
                UPDATE  "order"
                SET order_name = ?, order_description = ?
                WHERE id = ?
                """ ;
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Bunrong1211"
                );
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
                ){
            Order order = searchOrderById(id);
            if (order!= null){
                System.out.println(" [+] Inserted Order name : ");
                preparedStatement.setString(1,new Scanner(System.in).nextLine());
                System.out.println(" [+] Inserted Order description : ");
                preparedStatement.setString(2, new Scanner(System.in).nextLine());
                preparedStatement.setInt(3, id);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0){
                    System.out.println(" >_< Update Order Successfully ");
                    return rowsAffected;
                }else {
                    System.out.println(" Updated Order Failed");
                }
            }else {
                System.out.println(" Not Found order");
            }

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return 0;
    }

    @Override
    public Order searchOrderById(Integer id) {
        String sql = """
                SELECT * FROM "order" 
                WHERE id = ?
                """;
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Bunrong1211"
                );
                PreparedStatement preparedStatement
                        = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = null;
            while(resultSet.next()){
               order = Order.builder()
                       .id(resultSet.getInt("id"))
                       .orderName(resultSet.getString("order_name"))
                       .orderDescription(resultSet.getString("order_description"))
                       .customer(Customer.builder()
                               .id(resultSet.getInt("cus_id"))
                               .build())
                       .orderedAt(resultSet.getDate("ordered_at"))
                       .build();
            }
            return order;



        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());

        }
        return null;
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = """
        SELECT * FROM "order"
        INNER JOIN "customer" c ON "order".cus_id = c.id;
        """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Bunrong1211"
                );
                Statement statement = connection.createStatement();
                ){
            ResultSet resultSet = statement.executeQuery(sql);
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()){
                orderList.add(
                        Order
                                .builder()
                                .id(resultSet.getInt("id"))
                                .orderName(resultSet.getString("order_name"))
                                .orderDescription(resultSet.getString("order_description"))
                                .orderedAt(resultSet.getDate("ordered_at"))
                                .customer(Customer.builder()
                                        .id(resultSet.getInt("cus_id"))
                                        .name(resultSet.getString("name"))
                                        .email(resultSet.getString("email"))
                                        .password(resultSet.getString("password"))
                                        .isDeleted(resultSet.getBoolean("is_deleted"))
                                        .createdDate(resultSet.getDate("ordered_at"))

                                        .build())
                                .build()
                );
            }
            return orderList;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return List.of();
    }
}
