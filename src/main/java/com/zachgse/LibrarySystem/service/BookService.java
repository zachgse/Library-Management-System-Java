package com.zachgse.LibrarySystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zachgse.LibrarySystem.model.Book;

@Service
public interface BookService {
	List<Book> index();
	Book store(Book book);
	Book view (int id);
	Book update (Book existingBook);
	void updateStatus(int bookId, boolean status);
	void delete (int id);
	Book saveBook(Book book);
}
