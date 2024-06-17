package service;

import model.dto.OrderDto;
import model.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrder();
    int deleteOrder(Integer id);
    int updateOrder(Integer id);
    int addOrder(Order order);
}
