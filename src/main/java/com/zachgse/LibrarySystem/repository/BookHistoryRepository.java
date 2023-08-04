package com.zachgse.LibrarySystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zachgse.LibrarySystem.model.BookHistory;

public interface BookHistoryRepository extends JpaRepository<BookHistory, Integer>{
	@Query("SELECT h FROM BookHistory h " // Use the entity class name "BookHistory" instead of "book_repository"
			+ "INNER JOIN h.book b " // Use the alias "h" for BookHistory and "b" for Book
			+ "INNER JOIN h.user u " // Use the alias "h" for BookHistory and "u" for User
			+ "WHERE u.id = :user_id " // Use ":" to reference a named parameter "userId"
			+ "ORDER BY h.id, h.status DESC")
		List<BookHistory> findByUserId(@Param("user_id") int user_id);
}
