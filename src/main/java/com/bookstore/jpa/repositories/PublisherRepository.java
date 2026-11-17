package com.bookstore.jpa.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.jpa.models.PublisherModel;

public interface PublisherRepository extends JpaRepository<PublisherModel, UUID> {
    
    PublisherModel findPublisherModelByName(String name); //método para buscar uma editora pelo nome 
   // PublisherModel findPublisherModelById(UUID id); método para buscar uma editora pelo id

}
