package com.zachgse.LibrarySystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zachgse.LibrarySystem.model.User;
import com.zachgse.LibrarySystem.service.BookService;
import com.zachgse.LibrarySystem.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {
	@Autowired
	BookService bookService;
	
	@Autowired 
	UserService userService;
	
	@GetMapping("/")
	public ModelAndView index(ModelAndView mv) {
		mv.addObject("books", bookService.index());
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping("/register")
	public ModelAndView register(ModelAndView mv, User user) {
		mv.addObject("user", user);
		mv.setViewName("register");
		return mv;
	}
	
	@PostMapping("/register/store")
	public ModelAndView store(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		userService.store(user);
		redirectAttributes.addFlashAttribute("successMessage", "Registration successfully!");
		ModelAndView mv = new ModelAndView("redirect:/login");
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView login(ModelAndView mv) {
		mv.setViewName("login");
		return mv;
	}
	
	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

	@GetMapping("/logout")
	public ModelAndView performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
	    // .. perform logout
	    this.logoutHandler.logout(request, response, authentication);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
}
