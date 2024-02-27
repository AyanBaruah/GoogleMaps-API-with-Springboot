package com.Myproject.GoogleMapApi.MapController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Myproject.GoogleMapApi.Models.LocationRequest;
import com.Myproject.GoogleMapApi.Models.Order;
import com.Myproject.GoogleMapApi.MapServices.OrderRepository;
import com.Myproject.GoogleMapApi.MapServices.OrderService;
import com.Myproject.GoogleMapApi.MapServices.OrderServiceImpl;

@RestController
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@Autowired
	public OrderServiceImpl orderServiceImpl;
	
	
	private Long ID_INCREMENT=1L;

	
	@PostMapping("/orders")
	public Order getDistance(@RequestBody LocationRequest locationRequest) {
		Order orderResponse= new Order();
				int Dist= orderService.CalculateDistance(locationRequest);
				orderResponse.setDistance(Dist);
				orderResponse.setId(++ID_INCREMENT);
				orderResponse.setStatus("UNASSIGNED");
				orderService.AddOrder(orderResponse);
				
		return orderResponse;
	}
	
	@PatchMapping("/orders/{id}")
    public ResponseEntity<?> takeOrder(@PathVariable Long id) {
        try {
            String result = orderService.takeOrder(id);
            if ("SUCCESS".equals(result)) {
                return ResponseEntity.ok().body("{\"status\": \"SUCCESS\"}");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\": \"" + result + "\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
	
	@GetMapping("/orders")
    public ResponseEntity<?> getOrders(
            @RequestParam(name = "page") String pageParam,
            @RequestParam(name = "limit") String limitParam
    ) {
        try {
            int page = Integer.parseInt(pageParam);
            int limit = Integer.parseInt(limitParam);

            // Validate page and limit
            if (page < 1 || limit < 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"error\": \"Invalid page or limit value.\"}");
            }

            // Fetch orders based on page and limit (you need to implement this logic)
            List<Order> orders = orderService.getOrders(page, limit);

            // Check if the result is empty
            if (orders.isEmpty()) {
                return ResponseEntity.ok().body("[]");
            }

            // Return the list of orders
            return ResponseEntity.ok().body(orders);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Invalid page or limit format.\"}");
        }
    }

    

}
