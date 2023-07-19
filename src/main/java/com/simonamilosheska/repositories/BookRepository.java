package com.simonamilosheska.repositories;

import com.simonamilosheska.models.Author;
import com.simonamilosheska.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

  List<Book> findBookByAuthor(Author author);
  Optional<Book> findBookByNameAndAuthor(String bookName,Author author);

}
