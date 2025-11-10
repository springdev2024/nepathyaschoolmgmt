package com.example.demo.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository repository;

	@GetMapping("/products")
	public String getProducts(Model model) {
		model.addAttribute("products", repository.findAll());
		return "products.html";
	}
	
	@PostMapping("/product")
	public String saveNewProduct(@RequestParam("name") String name, @RequestParam("price") Double price) {
		/**
		 * code to store the data into database
		 */
		System.out.println("Name = " + name);
		System.out.println("Price = " + price);
				
		Product p = new Product(name, price);
		
		repository.save(p);
		
		return "redirect:/products";
	}

}
