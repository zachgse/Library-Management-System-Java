package com.zachgse.LibrarySystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zachgse.LibrarySystem.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	@Query("SELECT b FROM Book b ORDER BY b.id")
	List<Book> listById();
}
