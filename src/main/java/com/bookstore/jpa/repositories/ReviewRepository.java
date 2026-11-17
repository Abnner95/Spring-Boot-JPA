package com.bookstore.jpa.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.jpa.models.ReviewModel;

public interface ReviewRepository extends JpaRepository<ReviewModel, UUID> {
    
    ReviewModel findReviewModelByComment(String comment); //método para buscar uma review pelo comentário 
   // ReviewModel findReviewModelById(UUID id); método para buscar uma review pelo id

}
