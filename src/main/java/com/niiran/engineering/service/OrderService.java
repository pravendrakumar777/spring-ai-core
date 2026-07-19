package com.niiran.engineering.service;

import com.niiran.engineering.dto.OrderRequest;
import com.niiran.engineering.entity.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderRequest request);
    Order updateOrder(Long id, OrderRequest request);
    void cancelOrder(Long orderId);
    Order getOrderById(Long orderId);
    List<Order> getAllOrders();
    Long countOrders();
}
