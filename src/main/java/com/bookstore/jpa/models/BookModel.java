package com.bookstore.jpa.models;


// Pesquisar o que é Serializable
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity 
@Table(name ="TB_BOOK")

public class BookModel implements Serializable {
    //serialversion é uma serie 
    private static final long serialVersionUID = 1L;

    //Chave Primária
    @Id 
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    //Colunas da Tabela
    @Column(nullable = false, unique = true)
    private String title;

    //Relacionamento da tabela Book 
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Esconde a propriedade do JSON, para não aparecer na resposta da API
    @ManyToOne //Muitos Livros para uma editora (publisher)
    @JoinColumn(name = "publisher_id" ) //Fazendo o join na tabela Book com a tabela Publisher, criando uma coluna publisher_id na tabela Book
    private PublisherModel publisher;

    @ManyToMany// Muitos Livros para muitos Autores, vamos criar uma tabela auxiliar (tb_book_author) para fazer o relacionamento entre as tabelas Book e Author
    @JoinTable(
        name = "tb_book_author",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<AuthorModel> authors = new HashSet<>();

    // Relacionamento da tabela Book com a tabela Review 
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL) //parametro cascade = CascadeType.ALL é usado para propagar todas as operações de persistência (como salvar, atualizar, excluir) da entidade BookModel para a entidade ReviewModel. Isso significa que quando você realiza uma operação em um objeto BookModel, a mesma operação será automaticamente aplicada ao objeto ReviewModel associado.
    private ReviewModel review;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id){
        this.id = id;

    }

    public String getTitle(){
        return title;

    }

    public void setTitle(String title) {
        this.title = title;

    }

    public PublisherModel getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherModel publisher) {
        this.publisher = publisher;
    }

    public Set<AuthorModel> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorModel> authors) {
        this.authors = authors;
    }

    public ReviewModel getReview() {
        return review;
    }

    public void setReview(ReviewModel review) {
        this.review = review;
    }

}
