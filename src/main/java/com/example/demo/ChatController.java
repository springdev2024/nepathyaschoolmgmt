package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

	List<Chat> chats = new ArrayList<>();
	
	@GetMapping("/chats")
	public String getAllChats() {
		return "";
	}
	
}
