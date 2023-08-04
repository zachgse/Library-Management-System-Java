package com.zachgse.LibrarySystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zachgse.LibrarySystem.model.Book;
import com.zachgse.LibrarySystem.model.BookHistory;
import com.zachgse.LibrarySystem.model.User;
import com.zachgse.LibrarySystem.repository.BookHistoryRepository;
import com.zachgse.LibrarySystem.service.BookHistoryService;
import com.zachgse.LibrarySystem.service.BookService;
import com.zachgse.LibrarySystem.service.UserService;

@Service
public class BookHistoryServiceImplementation implements BookHistoryService{
	private BookHistoryRepository bookHistoryRepository;
	private BookService bookService;
	private UserService userService;
	
	public BookHistoryServiceImplementation(BookHistoryRepository bookHistoryRepository, BookService bookService, UserService userService) {
		this.bookHistoryRepository = bookHistoryRepository;
		this.bookService = bookService;
		this.userService = userService;
	}
	
	@Override
	public List<BookHistory> findByUser(User user) {
		return bookHistoryRepository.findByUserId(user.getId());
	}

    public BookHistory borrow_book(int book_id, int user_id) {
        BookHistory newBookHistory = new BookHistory();
        Book book = bookService.view(book_id);
        User user = userService.view(user_id);
        newBookHistory.setBook(book);
        newBookHistory.setUser(user);
        newBookHistory.setStatus(true);
        return bookHistoryRepository.save(newBookHistory);
    }
    
    public BookHistory return_book(int book_id, int user_id) {
        BookHistory newBookHistory = new BookHistory();
        Book book = bookService.view(book_id);
        User user = userService.view(user_id);
        newBookHistory.setBook(book);
        newBookHistory.setUser(user);
        newBookHistory.setStatus(false);
        return bookHistoryRepository.save(newBookHistory);
    }

}
