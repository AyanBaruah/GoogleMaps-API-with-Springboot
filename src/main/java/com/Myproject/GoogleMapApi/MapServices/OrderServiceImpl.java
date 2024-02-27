package com.Myproject.GoogleMapApi.MapServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.Myproject.GoogleMapApi.Models.LatLng;
import com.Myproject.GoogleMapApi.Models.Location;
import com.Myproject.GoogleMapApi.Models.LocationRequest;
import com.Myproject.GoogleMapApi.Models.Place;
import com.Myproject.GoogleMapApi.Models.RequestGoogleDist;
import com.Myproject.GoogleMapApi.Models.Order;
import com.Myproject.GoogleMapApi.Models.Route;
import com.Myproject.GoogleMapApi.Models.RouteResponse;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;

    public OrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	
	public int CalculateDistance( LocationRequest locationRequest) {
		
		String url = "https://routes.googleapis.com/directions/v2:computeRoutes";
		
		RequestGoogleDist requestGoogleDist = new RequestGoogleDist();
		String Startlat= locationRequest.getOrigin().get(0);
		String Startlng= locationRequest.getOrigin().get(1);
		String endlat= locationRequest.getDestination().get(0);
		String endlng= locationRequest.getDestination().get(1);
		
				try {
				    Double doubleStartlat = Double.valueOf(Startlat);
				    Double doubleStartlng = Double.valueOf(Startlng);
				    Double doublendlat = Double.valueOf(endlat);
				    Double doubleendlng = Double.valueOf(endlng);
				    System.out.println("Converted Double value: " + doubleStartlat +" "+ doubleStartlng+" "+doublendlat+" "+doubleendlng);
				
		
		//Set Google Req
		LatLng originLatLng = new LatLng();
        originLatLng.setLatitude(doubleStartlat);
        originLatLng.setLongitude(doubleStartlng);

        Location originLocation = new Location(originLatLng);
        originLocation.setLatlan(originLatLng);

        Place originPlace = new Place(originLocation);
        originPlace.setLocation(originLocation);

        // Set values for destination
        LatLng destinationLatLng = new LatLng();
        destinationLatLng.setLatitude(doublendlat);
        destinationLatLng.setLongitude(doubleendlng);

        Location destinationLocation = new Location(destinationLatLng);
        destinationLocation.setLatlan(destinationLatLng);

        Place destinationPlace = new Place(destinationLocation);
        destinationPlace.setLocation(destinationLocation);

        // Set travel mode
        requestGoogleDist.setOrigin(originPlace);
        requestGoogleDist.setDest(destinationPlace);
        requestGoogleDist.setTravel("DRIVE");
        
     // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Goog-Api-Key", "AIzaSyAT2o2Z8HGr-A-zb3SJX290cOk5ljKYrt0");
        headers.set("X-Goog-FieldMask", "routes.distanceMeters");
        
     // Create the HTTP entity with the request body and headers
        HttpEntity<RequestGoogleDist> httpEntity = new HttpEntity<>(requestGoogleDist, headers);

        // Make the POST request
        RouteResponse response= restTemplate.postForObject(url, httpEntity, RouteResponse.class);

        List<Route> routes = response.getRoutes();
        int distanceMeters = routes.get(0).getDistanceMeters();
        
		return distanceMeters;
				} catch (NumberFormatException e) {
				    System.out.println("Invalid double format: " + e.getMessage());
				    return -1;
				}
	}
	
	public void AddOrder(Order order) {
		orderRepository.save(order);
	}
	
	
	@Transactional
    public String takeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            return "Order not found";
        }

        if ("TAKEN".equals(order.getStatus())) {
            return "Order has already been taken";
        }

        // Simulate processing time
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        order.setStatus("TAKEN");
        orderRepository.save(order);

        return "SUCCESS";
    }

	
	
	public List<Order> getOrders(int page, int limit) {
        if (page < 1 || limit < 1) {
            throw new IllegalArgumentException("Page and limit must be positive integers.");
        }

        int offset = (page - 1) * limit;
        return orderRepository.findAllOrdersWithPageAndLimit(offset, limit);
    }




    
}
	
	


