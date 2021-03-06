package com.webapp.animeshop.user;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.order.Order;
import com.webapp.animeshop.order.OrderRepository;
import com.webapp.animeshop.product.Product;


@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	public UserService userService;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public UserComponent userComponent;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@PostMapping("/user")
	public ResponseEntity<?> addNewUser(@RequestBody User user) {
		if(userComponent.getLoggedUser()!=null)
			return new ResponseEntity<>("Please, loggout first to register a new user", HttpStatus.CONFLICT);
		User newUser = new User(user.getName(), user.getPasswordHash(), user.getDelivery(), "ROLE_USER");
		if (userRepository.findByName(user.getName()) == null) {
			Order order = new Order();
			newUser.getOrderList().add(order);
			newUser.setDelivery(new Address());
			userService.save(newUser);
			order.setUser(newUser);
			this.orderRepository.save(order);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/user/{id}")
	public User getCurrentUser(@PathVariable long id) { 
		User user = userComponent.getLoggedUser();
		if(user.getId()==userRepository.findById(id).getId())
			user = userRepository.findById(id);
		return user;
	}
	
	@GetMapping("/user")
	public List<User> getAllUsers(){
		return userService.findAll();
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> newInfo(@PathVariable long id, @RequestParam String shippingName, @RequestBody Address address, @RequestParam String passwordHash) {
		User user = this.userComponent.getLoggedUser();
		if(user!=null)
			user = userRepository.findByName(user.getName());
		else
			return new ResponseEntity<>("You are not logged already", HttpStatus.UNAUTHORIZED);
		if(user.getId()!=id)
			return new ResponseEntity<>("You can't modify other users information", HttpStatus.UNAUTHORIZED);
		user.setDelivery(address);
		user.getDelivery().setName(shippingName);
		user.setPassword(passwordHash);
		this.userRepository.save(user);		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}
