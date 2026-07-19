package com.niiran.engineering.tools;

import com.niiran.engineering.dto.OrderRequest;
import com.niiran.engineering.entity.Order;
import com.niiran.engineering.service.OrderService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderTool {
    private final OrderService orderService;

    public OrderTool(OrderService orderService) {
        this.orderService = orderService;
    }

    @Tool(description = "Create a new order")
    public Order createOrder(OrderRequest request) {
        return orderService.createOrder(request);
    }

    @Tool(description = "Update an order")
    public Order updateOrder(Long id, OrderRequest request) {
        return orderService.updateOrder(id, request);
    }

    @Tool(description = "Cancel an order")
    public String cancelOrder(Long orderId) {
        orderService.cancelOrder(orderId);
        return "Order cancelled successfully.";
    }

    @Tool(description = "Get order by id")
    public Order getOrderById(Long orderId) {
        return orderService.getOrderById(orderId);
    }
    @Tool(description = "List all orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Tool(description = "Count total orders")
    public Long countOrders() {
        return orderService.countOrders();
    }
}
