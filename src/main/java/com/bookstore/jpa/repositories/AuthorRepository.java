package com.bookstore.jpa.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.jpa.models.AuthorModel;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {
    
    AuthorModel findAuthorModelByName(String name); //método para buscar um autor pelo nome 
   // AuthorModel findAuthorModelById(UUID id); método para buscar um autor pelo id

}
