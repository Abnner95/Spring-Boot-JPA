package com.bookstore.jpa.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.jpa.models.BookModel;

public interface BookRepository extends JpaRepository<BookModel, UUID> {
    
    BookModel findBookModelByTitle(String title); //método para buscar um livro pelo título 
   // BookModel findBookModelById(UUID id); método para buscar um livro pelo id

   //método para buscar livros pelo id da editora (publisher) utilizando QUERY e @Param para passar o parâmetro id da editora
    // @Query(value ="SELECT * FROM tb_book WHERE publisher_id = :id", nativeQuery = true)
    // List<BookModel> findBooksBY PublisherId(@Param ("id") UUID id);

}
