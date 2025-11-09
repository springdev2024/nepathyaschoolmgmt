package com.example.demo.store;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

	List<Product> products = new ArrayList<>();

	@GetMapping("/products")
	public String getProducts(Model model) {
		model.addAttribute("products", products);
		return "products.html";
	}

	@GetMapping("/product")
	public String getNewProductPage() {
		return "newProduct.html";
	}
	
	@PostMapping("/product")
	public String saveNewProduct(@RequestParam("name") String name, @RequestParam("price") Double price) {
		/**
		 * code to store the data into database
		 */
		return "newProduct.html";
	}

}
