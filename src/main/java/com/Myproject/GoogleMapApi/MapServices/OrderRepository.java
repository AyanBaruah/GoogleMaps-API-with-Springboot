package com.Myproject.GoogleMapApi.MapServices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Myproject.GoogleMapApi.Models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o ORDER BY o.id")
    List<Order> findAllOrders();

	@Query("SELECT o FROM Order o ORDER BY o.id")
    List<Order> findAllOrdersWithPageAndLimit(
            @Param("offset") int offset,
            @Param("limit") int limit
    );
}
