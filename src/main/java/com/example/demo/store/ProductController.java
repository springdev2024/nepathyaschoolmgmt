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
		// upsert object in database
		repository.save(product);
		return "redirect:/products";
	}
	
	
	/**
	 * Mapping to display edit form
	 */
	@GetMapping("/products/edit/{id}")
	public String getProductEditPage(@PathVariable("id") int productID, Model model) {
		model.addAttribute("product", repository.findById(productID).get());
		model.addAttribute("products", repository.findAll());
		model.addAttribute("forUpdate", true);
		return "products.html";
	}
	
	@PostMapping("/products/delete")
	public String deleteProductByID(@RequestParam("id") int productID) {
		repository.deleteById(productID);
		return "redirect:/products";
	}
	
}
