package com.zachgse.LibrarySystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zachgse.LibrarySystem.model.Book;
import com.zachgse.LibrarySystem.repository.BookRepository;
import com.zachgse.LibrarySystem.service.BookService;

@Service
public class BookServiceImplementation implements BookService {
	
	private BookRepository bookRepository;

	public BookServiceImplementation(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> index() {
		return bookRepository.listById();
	}

	@Override
	public Book store(Book book) {
		book.setStatus(true);
		return bookRepository.save(book);
	}

	@Override
	public Book view(int id) {
		return bookRepository.findById(id).get();
	}

	@Override
	public Book update(Book existingBook) {
		return bookRepository.save(existingBook);
	}
	
    @Override
    public void updateStatus(int bookId, boolean status) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            book.setStatus(status);
            bookRepository.save(book);
        }
    }
   public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

	@Override
	public void delete(int id) {
		bookRepository.deleteById(id);
	}

}
