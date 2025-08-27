package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserRegistration {

	List<LoginInfo> allLoggedInUsers = new ArrayList<>();

	@GetMapping("/")
	public String getHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// check if user has token
		Cookie[] cookies = request.getCookies();
		boolean userLoggedIn = false;
		String token = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("SECUREID")) {
					userLoggedIn = true;
					token = cookie.getValue();
					break;
				}
			}
		}

		if (userLoggedIn) {
			// user's cookie has SECUREID (which was probably given by the server)
			// TODO: greet the user
			String username = null;
			for(LoginInfo loginInfo: allLoggedInUsers) {
				if(loginInfo.getToken().equals(token)) {
					username = loginInfo.getUsername();
					break;
				}
			}
			return "<h2>Hello, " + username + "!</h2>";
		} else {
			response.sendRedirect("/login");
		}

		return "";
	}

	@GetMapping("/login")
	public String getLoginPage() {
		return """
				<form action="/login/save" method="get">
				<input type="text" placeholder="Username" name="username" />
				<input type="submit" value="LOGIN" />
				</form>""";
	}

	@GetMapping("/login/save")
	public String saveLoginPage(@RequestParam("username") String username, HttpServletResponse response) {
		// TODO: generate, save and provide new token to this user.

		// 1. Generate token
		String token = UsefulMethods.getRandomString(20);

		// 2. Save token to database along with username
		LoginInfo loginInfo = new LoginInfo(username, token);
		allLoggedInUsers.add(loginInfo);

		// 3. send token to user/client/browser
		Cookie cookie = new Cookie("SECUREID", token);
		cookie.setPath("/");
		response.addCookie(cookie);

		return "Login submitted!";   // redirect to dashboard, homepage
	}

}
