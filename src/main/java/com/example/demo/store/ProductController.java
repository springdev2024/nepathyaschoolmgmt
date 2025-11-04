package com.example.demo.store;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	
	Product[] products = {
			new Product("Mouse", 950.0),
			new Product("USB Headset", 2700.0),
			new Product("Laptop", 128000.0)
	};

	@GetMapping("/products")
	public String getProducts(Model model) {
		model.addAttribute("products", products);
		return "products.html";
	}
	
}
