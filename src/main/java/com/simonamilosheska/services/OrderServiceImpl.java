package com.simonamilosheska.services;

import com.simonamilosheska.exceptions.NotExistEntityException;
import com.simonamilosheska.models.Client;
import com.simonamilosheska.models.Order;
import com.simonamilosheska.repositories.ClientRepository;
import com.simonamilosheska.repositories.OrderRepository;
import com.simonamilosheska.requests.OrderRequest;
import com.simonamilosheska.responses.OrderDto;
import com.simonamilosheska.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final ClientRepository clientRepository;

  @Autowired
  public OrderServiceImpl(
    OrderRepository orderRepository, ClientRepository clientRepository) {
    this.orderRepository = orderRepository;
    this.clientRepository = clientRepository;
  }

  @Override
  public List<OrderDto> getAllOrders() {
    return orderRepository.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
  }

  @Override
  public List<OrderDto> getOrdersByClientId(int clientId) {
    Client client = clientRepository.findById(clientId).orElse(null);
    if (client != null) {
      return orderRepository.findAllByClient(client).stream().map(OrderDto::new).collect(Collectors.toList());
    } else {
      throw new NotExistEntityException(String.format("Client with %d id doesn't have orders", clientId));
    }
  }

  @Override
  public List<OrderDto> getOrdersByIssueDate(String date, boolean flag) {
    if (flag) {
      return orderRepository.findAllOrdersBeforeIssueDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))).stream().map(OrderDto::new)
                            .collect(Collectors.toList());
    } else {
      return orderRepository.findAllOrdersAfterIssueDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))).stream().map(OrderDto::new)
                            .collect(Collectors.toList());
    }
  }

  @Override
  public List<OrderDto> getOrdersByDueDate(String date, boolean flag) {
    if (flag) {
      return orderRepository.findAllOrdersBeforeDueDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))).stream().map(OrderDto::new)
                            .collect(Collectors.toList());
    } else {
      return orderRepository.findAllOrdersAfterDueDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))).stream().map(OrderDto::new)
                            .collect(Collectors.toList());
    }
  }

  @Override
  public Order createOrder(OrderRequest orderRequest) {
    Order order =
      new Order(orderRequest.getClient(), orderRequest.getBooks(), LocalDate.now(), LocalDate.now().plusDays(30));
    return orderRepository.save(order);
  }
}
