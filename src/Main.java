
import model.dao.CustomerDaoImpl;
import model.dao.OrderDaoImpl;
import model.dao.ProductDaoImpl;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {







//        List <Integer> productId = new ArrayList<>(List.of(1,2));
//        for (Integer i : productId){
//            new OrderDaoImpl()
//                    .addNewOrder(Order
//                            .builder()
//                            .id(1)
//                            .orderName("Pizza")
//                            .orderDescription("Sea Food")
//                            .orderedAt(Date.valueOf(LocalDate.now()))
//                            .customer(Customer
//                                    .builder()
//                                    .id(2)
//                                    .build())
//                            .productList(new ArrayList<>(
//                                    List.of(Product.builder()
//                                            .id(i)
//                                            .build())
//                            ))
//                            .build());
//        }

//        new OrderDaoImpl()
//                .updateOrderById(2);

//      Deleted Order
//    new OrderDaoImpl()
//            .deleteOrderById(3);



//        new OrderDaoImpl()

//        Show ordered

//        new OrderDaoImpl()
//                .queryAllOrders()
//                .forEach(System.out::println);


//        order Item
//        new OrderDaoImpl()
//                .addNewOrder(Order
//                        .builder()
//                        .id(5)
//                        .orderName("Ice-Latte")
//                        .orderDescription("Sugar 50%") // Ensure this matches the field name in Order.java
//                        .customer(Customer
//                                .builder()
//                                .id(2)
//                                .build())
//                        .orderedAt(Date.valueOf(LocalDate.now()))
//                        .build());
//              Add new Customers
//        new CustomerDaoImpl()
//                .addNewCustomer(
//                        Customer
//                                .builder()
//                                .id(3)
//                                .name("Puii")
//                                .email("puii@gmail.com")
//                                .password("124433@")
//                                .isDeleted(false)
//                                .createdDate(Date.valueOf(LocalDate.now()))
//                                .build()
//                );


//        new CustomerDaoImpl()
//                .deleteCustomerByID(1);

//      Show all customers
//        new CustomerDaoImpl()
//                .queryAllCustomers()
//                .stream()
//                .filter(e->e.getId().equals(1))
//                .forEach(System.out::println);
//


    }
}