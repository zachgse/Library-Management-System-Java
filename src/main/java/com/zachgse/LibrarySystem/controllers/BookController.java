package com.zachgse.LibrarySystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zachgse.LibrarySystem.model.Book;
import com.zachgse.LibrarySystem.service.BookService;

@Controller
public class BookController {
	private BookService bookService; 
	
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	
	@GetMapping("/books/index")
	public ModelAndView index(ModelAndView mv) {
		mv.addObject("books", bookService.index());
		mv.setViewName("/book/index");
		return mv;
	}
	

	@GetMapping("/books/create") 
	public ModelAndView create(ModelAndView mv) {
		Book book = new Book();
		mv.addObject("book", book);
		mv.setViewName("/book/create");
		return mv;
	}
	
	@PostMapping("/books/store") 
	public ModelAndView store(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
		bookService.store(book);
		redirectAttributes.addFlashAttribute("successMessage", "Book added successfully!");
		ModelAndView mv = new ModelAndView("redirect:/books/index");
		return mv;
	}
	
	@GetMapping("/books/edit/{id}")
	public ModelAndView edit(@PathVariable int id, ModelAndView mv) {
		mv.addObject("book", bookService.view(id));
		mv.setViewName("/book/edit");
		return mv;
	}
	
	@PostMapping("/books/update/{id}") 
	public ModelAndView update(@PathVariable int id, Book book,RedirectAttributes redirectAttributes) {
		Book existingBook = bookService.view(id);
		existingBook.setName(book.getName());
		existingBook.setGenre(book.getGenre());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setYear(book.getYear());
		bookService.update(existingBook);
		redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully!");
		ModelAndView mv = new ModelAndView("redirect:/books/index");
		return mv;
	}
	
//	@GetMapping("/books/status/{id}")
//	public ModelAndView update_status(@PathVariable int id, RedirectAttributes redirectAttributes) {
//	    bookService.update_status(id);
//	    redirectAttributes.addFlashAttribute("successMessage", "Book status updated successfully!");
//	    ModelAndView mv = new ModelAndView("redirect:/books/index");
//	    return mv;
//	}
	
	@GetMapping("/admin")
	public ModelAndView admin(ModelAndView mv) {
		mv.setViewName("admin");
		
		return mv;
	}

	@GetMapping("/authentication/login")
	public ModelAndView login(ModelAndView mv) {
		mv.setViewName("login");
		
		return mv;
	}
}
