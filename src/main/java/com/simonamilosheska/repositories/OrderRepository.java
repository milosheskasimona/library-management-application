package com.simonamilosheska.repositories;

import com.simonamilosheska.models.Client;
import com.simonamilosheska.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  List<Order> findAll();

  List<Order> findAllByClient(Client client);

  @Query("select a from Order a where a.issueDate <= :filterDate")
  List<Order> findAllOrdersBeforeIssueDate(
    @Param("filterDate") LocalDate filterDate);

  @Query("select a from Order a where a.issueDate >= :filterDate")
  List<Order> findAllOrdersAfterIssueDate(
    @Param("filterDate") LocalDate filterDate);

  @Query("select a from Order a where a.dueDate <= :filterDate")
  List<Order> findAllOrdersBeforeDueDate(
    @Param("filterDate") LocalDate filterDate);

  @Query("select a from Order a where a.dueDate >= :filterDate")
  List<Order> findAllOrdersAfterDueDate(
    @Param("filterDate") LocalDate filterDate);
}
