package controller;

import model.dto.OrderDto;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.service.OrderService;
import model.service.OrderServiceImpl;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private static OrderService orderService = new OrderServiceImpl();

    public void getAllOrder(){
        List<OrderDto> orderDtoList = orderService.getAllOrder();

        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.setColumnWidth(0, 10, 20);
        table.setColumnWidth(1, 20, 40);
        table.setColumnWidth(2, 40, 60);
        table.setColumnWidth(3, 30, 50);
        table.setColumnWidth(4, 30, 50);

        table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Order Name", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Order Description", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Customer Name", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Ordered At", new CellStyle(CellStyle.HorizontalAlign.CENTER));

        for (OrderDto orderDto : orderDtoList) {
            table.addCell(String.valueOf(orderDto.id()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(orderDto.orderName(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(orderDto.orderDescription(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(orderDto.customerName(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(String.valueOf(orderDto.orderedAt()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
        }

        System.out.println(table.render());
    }

    public void addNewOrder(){

        System.out.print("🧋 Order Name: ");
        String oName = new Scanner(System.in).next();
        System.out.print("📜 Order Description: ");
        String oDesc = new Scanner(System.in).next();
        System.out.print("🏷️ Product ID: ");
        int pId = new Scanner(System.in).nextInt();
        System.out.print("👤 Customer ID: ");
        int cId = new Scanner(System.in).nextInt();
        orderService.addOrder(Order.builder()
                        .id(1)
                        .orderName(oName)
                        .orderDescription(oDesc)
                        .customer(Customer.builder()
                                .id(cId)
                                .build())
                        .productList(new ArrayList<>(List.of(Product.builder()
                                        .id(pId)
                                .build())))
                        .orderedAt(Date.valueOf(LocalDate.now()))
                .build());
    }

    public void deleteOrder() {
        System.out.print("🆔 Order ID: ");
        int id = new Scanner(System.in).nextInt();
        orderService.deleteOrder(id);
    }

    public void updateOrder(){
        System.out.print("🆔 Order ID: ");
        int id = new Scanner(System.in).nextInt();
        orderService.updateOrder(id);
    }
}
