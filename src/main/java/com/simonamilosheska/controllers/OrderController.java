package com.simonamilosheska.controllers;

import com.simonamilosheska.models.Order;
import com.simonamilosheska.requests.OrderRequest;
import com.simonamilosheska.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import com.simonamilosheska.responses.OrderDto;
import java.net.URI;
import java.util.List;

@Controller
public class OrderController {

  OrderServiceImpl orderService;

  @Autowired
  public OrderController(OrderServiceImpl orderService) {
    this.orderService = orderService;
  }
  @PreAuthorize("hasAnyRole('LIBRARIAN')")
  @GetMapping("/orders")
  public ResponseEntity<List<OrderDto>> getAllOrders() {
    List<OrderDto> orders = orderService.getAllOrders();
    return ResponseEntity.ok(orders);
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN')")
  @GetMapping(value = "/clients/{clientId}/orders")
  public ResponseEntity<List<OrderDto>> getOrdersByClient(@PathVariable  int clientId) {
    List<OrderDto> orders = orderService.getOrdersByClientId(clientId);
    return ResponseEntity.ok(orders);
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN')")
  @RequestMapping(value = "/orders", params = "beforeIssue")
  public ResponseEntity<List<OrderDto>> getBookBeforeIssueDate(@RequestParam("beforeIssue") String beforeDate) {
    List<OrderDto> orders = orderService.getOrdersByIssueDate(beforeDate, true);
    return ResponseEntity.ok(orders);
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN')")
  @RequestMapping(value = "/orders", params = "afterIssue")
  public ResponseEntity<List<OrderDto>> getBookAfterIssueDate(@RequestParam("afterIssue") String afterDate) {
    List<OrderDto> orders = orderService.getOrdersByIssueDate(afterDate, false);
    return ResponseEntity.ok(orders);
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN')")
  @RequestMapping(value = "/orders", params = "beforeDue")
  public ResponseEntity<List<OrderDto>> getBookBeforeDueDate(@RequestParam("beforeDue") String beforeDate) {
    List<OrderDto> orders = orderService.getOrdersByDueDate(beforeDate, true);
    return ResponseEntity.ok(orders);
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN')")
  @RequestMapping(value = "/orders", params = "afterDue")
  public ResponseEntity<List<OrderDto>> getBookAfterDueDate(@RequestParam("afterDue") String afterDate) {
    List<OrderDto> orders = orderService.getOrdersByDueDate(afterDate, false);
    return ResponseEntity.ok(orders);
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN')")
  @PostMapping("/orders")
  public ResponseEntity<Void> createOrder(@RequestBody OrderRequest orderRequest) {
    Order order = orderService.createOrder(orderRequest);

    URI location = UriComponentsBuilder.fromUriString("/orders/{id}")
                                       .buildAndExpand(order.getId())
                                       .toUri();

    return ResponseEntity.created(location).build();
  }
}
