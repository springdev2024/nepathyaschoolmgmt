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

	public static List<LoginInfo> allLoggedInUsers = new ArrayList<>();

	@GetMapping("/")
	public String getHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// check if user has token in their cookies
		LoginInfo info = UsefulMethods.getLoggedInUser(request);
		if (info != null) {
			// user's cookie has SECUREID (which was probably given by the server)
			return "<h2>Hello, " + info.getUsername() + "!</h2>" + """
					<a href="/profile">Profile</a>
					""" + "<p>" + info.getFullName() + " • " + info.getEmail() + "</p>";
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

	@GetMapping("/profile")
	public String getProfilePage(HttpServletRequest request, HttpServletResponse response) throws IOException {

		LoginInfo info = UsefulMethods.getLoggedInUser(request);

		if (info != null) {
			// user's cookie has SECUREID (which was probably given by the server)
			return "<h2>Hello, " + info.getUsername() + "!</h2>" + """
					<form action="/profile/save" method="get">
					<input type="text" placeholder="Full Name" name="fullName" />
					<input type="text" placeholder="Email" name="email" />
					<input type="submit" value="SAVE" />
					</form>""";
		} else {
			response.sendRedirect("/login");
		}
		return "";
	}

	@GetMapping("/profile/save")
	public String saveProfile(@RequestParam("fullName") String fullName, @RequestParam("email") String email,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		LoginInfo info = UsefulMethods.getLoggedInUser(request);

		if (info != null) {
			// actually save the fullname & email in database
			info.setFullName(fullName);
			info.setEmail(email);

			response.sendRedirect("/");

		} else {
			response.sendRedirect("/login");
		}
		return "";
	}

	@GetMapping("/login/save")
	public String saveLoginPage(@RequestParam("username") String username, HttpServletResponse response)
			throws IOException {
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

		response.sendRedirect("/");

		return "";
	}

}
