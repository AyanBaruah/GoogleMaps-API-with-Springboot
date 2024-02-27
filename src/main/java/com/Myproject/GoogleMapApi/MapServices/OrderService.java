package com.Myproject.GoogleMapApi.MapServices;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Myproject.GoogleMapApi.Models.LocationRequest;
import com.Myproject.GoogleMapApi.Models.Order;

@Service
public interface OrderService {

	public int CalculateDistance(LocationRequest locationRequest);

	public String takeOrder(Long orderId);

	public void AddOrder(Order order);

	//public List<Order> fetchOrders(int page, int limit);
	
	public List<Order> getOrders(int page, int limit);

}
