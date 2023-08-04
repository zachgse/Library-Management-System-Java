package com.zachgse.LibrarySystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zachgse.LibrarySystem.model.User;
import com.zachgse.LibrarySystem.service.BookHistoryService;
import com.zachgse.LibrarySystem.service.BookService;
import com.zachgse.LibrarySystem.service.UserService;
import com.zachgse.LibrarySystem.service.impl.MyUserDetails;

@Controller
public class RentController {
    private BookService bookService;
    private BookHistoryService bookHistoryService;
    private UserService userService;
	
    @Autowired
    public RentController(BookService bookService, BookHistoryService bookHistoryService, UserService userService) {
        this.bookService = bookService;
        this.bookHistoryService = bookHistoryService;
        this.userService = userService;
    }
	
    public int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            // User is not authenticated
            // You may handle this case as needed, e.g., throw an exception or return a default value
            return -1; // or throw new AuthenticationException("User not authenticated");
        }

        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        return myUserDetails.getId();
    }
    
    @GetMapping("/books/rent/{id}")
    public ModelAndView rent_book(@PathVariable int id, RedirectAttributes redirectAttributes) {
        int book_id = id;
        int user_id = this.getCurrentUserId();
        bookHistoryService.borrow_book(book_id, user_id);
        bookService.updateStatus(id, false);
        redirectAttributes.addFlashAttribute("successMessage", "Book borrowed successfully!");
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;
    }

    @GetMapping("/books/return/{id}")
    public ModelAndView return_book(@PathVariable int id, RedirectAttributes redirectAttributes) {
        int book_id = id;
        int user_id = this.getCurrentUserId();
        bookHistoryService.return_book(book_id, user_id);
        bookService.updateStatus(id, true);
        redirectAttributes.addFlashAttribute("successMessage", "Book returned successfully!");
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;
    }
    
    @GetMapping("/books/profile/history")
    public ModelAndView history_book() {
        ModelAndView mv = new ModelAndView("/profile/history");
        int userId = this.getCurrentUserId();
        User user = userService.view(userId);
        mv.addObject("bookHistories", bookHistoryService.findByUser(user));
        return mv;
    }


}
