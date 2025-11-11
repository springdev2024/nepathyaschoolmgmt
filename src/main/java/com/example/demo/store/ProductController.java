package com.example.demo.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository repository;

	@GetMapping("/products")
	public String getProducts(Model model) {
		model.addAttribute("products", repository.findAll());
		model.addAttribute("forUpdate", false);
		model.addAttribute("product", new Product());
		return "products.html";
	}
	
	@PostMapping("/product")
	public String saveNewProduct(Product product) {
		/**
		 * code to store the data into database
		 */
//		System.out.println("Name = " + produ);
//		System.out.println("Price = " + price);
				
		repository.save(product);
		return "redirect:/products";
	}
	
	
	/**
	 * Mapping to display edit form
	 */
	@GetMapping("/products/edit/{name}")
	public String getProductEditPage(@PathVariable("name") String productName, Model model) {
		model.addAttribute("products", repository.findAll());
		model.addAttribute("forUpdate", true);
		model.addAttribute("product", repository.findByName(productName));
		return "products.html";
	}
	
}
