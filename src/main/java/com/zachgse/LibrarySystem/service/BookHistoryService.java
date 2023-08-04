package com.zachgse.LibrarySystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zachgse.LibrarySystem.model.BookHistory;
import com.zachgse.LibrarySystem.model.User;

@Service
public interface BookHistoryService {
	List<BookHistory> findByUser(User user);
	BookHistory borrow_book(int book_id, int user_id);
	BookHistory return_book(int book_id, int user_id);
}
