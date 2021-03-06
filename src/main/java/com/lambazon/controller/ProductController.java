package com.lambazon.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lambazon.domain.Product;
import com.lambazon.service.ProductService;


@Controller
public class ProductController {
	
	@Inject
	private ProductService productService;
	
	@GetMapping("/products")
	public String products	(Model model) {
		model.addAttribute("prods", productService.products());
		model.addAttribute("totalInventoryAmount", calculateTotalInventoryAmount());
		return "products";
	}
	
	@GetMapping("/products/{id}/details")
	public String product	(@PathVariable Integer id, Model model) {
		model.addAttribute("prod", productService.product(id));
		return "product";
	}

    /**
     * there is an error in the code.
     * The number 123456.78 is commented.
     * I delete unnecessary lines.
     */
	private double calculateTotalInventoryAmount() {
		double totalInventoryAmount = 0.0;
		for (Product p : productService.products()) {
			totalInventoryAmount+=p.getInventoryPrice();
		}

		return totalInventoryAmount;


	}
}
