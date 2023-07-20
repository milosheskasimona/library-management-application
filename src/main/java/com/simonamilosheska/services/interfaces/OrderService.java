package com.simonamilosheska.services.interfaces;

import com.simonamilosheska.models.Order;
import com.simonamilosheska.requests.OrderRequest;
import com.simonamilosheska.responses.OrderDto;

import java.util.List;

public interface OrderService {

  List<OrderDto> getAllOrders();

  List<OrderDto> getOrdersByClientId(int clientId);

  List<OrderDto> getOrdersByIssueDate(String dat, boolean flag);

  List<OrderDto> getOrdersByDueDate(String date, boolean flag);

  Order createOrder(OrderRequest orderRequest);
}
