package com.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cart.model.User;
import com.cart.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String processRegister(@ModelAttribute User user, Model model) {
		if (userService.emailExists(user.getEmail())) {
			model.addAttribute("error", "Email 已被註冊！");
			return "register";
		}
		userService.register(user);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); 
		return "redirect:/login";
	}

}
