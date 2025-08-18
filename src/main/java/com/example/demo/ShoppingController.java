package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ShoppingController {
	List<ShoppingItem> shoppingList = new ArrayList<>();


	/**
	 * create a method to show the collected shopping list items 1. loop through
	 * shoppingList[] -> concatenate shoppingList[i] with result result = result +
	 * shoppingList[i] 2. return result;
	 */
	@GetMapping("/shopping/list")
	public String getShowShoppingListPage() {
		String result = "<p>My shopping list:</p>";

		if (shoppingList.isEmpty()) {
			result = result + "<p><b>No items</b></p>";
		} else {
			result = result + "<ul>"; // begin the list
			for (int i = 0; i < shoppingList.size(); i++) {
				result = result + "<li><span>" + shoppingList.get(i) + "</span>" + "<a href=\"/shopping/delete/" + i
						+ "\"> Remove </a>" + "</li>";
			}
			result = result + "</ul>"; // end the list
		}
		// You can also add other HTML codes here

		// Can you put the form to add new item in this place?
		result = result + """
					<form action="/shopping/save" method="get">
					<input type="text" name="item" />
					<input type="submit" value="ADD ITEM" />
				</form>
				""";

		System.out.println(result); // print raw HTML
		return result;
	}

	@GetMapping("/shopping/save")
	public String saveNewShoppingItem(@RequestParam("item") String item, HttpServletResponse resp) throws IOException {
//		shoppingList.add(item);
		shoppingList.add(new ShoppingItem(item, "dummyuser"));
		resp.sendRedirect("/shopping/list"); // browser goes to this url
		return "Item added!";
	}

	// TASK: Try to delete an item from the shopping list
	@GetMapping("/shopping/delete/{index}")
	public String deleteShoppingItem(@PathVariable("index") int index, HttpServletResponse resp) throws IOException {
		if (index < shoppingList.size()) {
			shoppingList.remove(index);
		}
		resp.sendRedirect("/shopping/list"); // browser goes to this url
		return "Item deleted!";
	}
}
