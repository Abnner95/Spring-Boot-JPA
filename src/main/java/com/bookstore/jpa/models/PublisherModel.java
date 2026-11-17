package com.bookstore.jpa.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity 
@Table(name ="TB_PUBLISHER") //tabela Publisher
public class PublisherModel implements Serializable {
    private static final long serialVersionUID = 1L;
 
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;


    // para que serve uma serialização ? (Pesquisar)

    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //mappedBy é onde esta mapeado de onde esta vindo o join na tb book
    @OneToMany(mappedBy="publisher", fetch=FetchType.LAZY) //fetch significa que quando busca a entidade na base de dados, ele faz um carregamento lento (para buscar só o necessário) <> EAGEr 
    private Set<BookModel> books = new HashSet<>();
    // para que serve um getter and setter 
    public UUID getId() {
        return id;
    }

    public void setId(UUID id){
        this.id = id;

    }

    public String getTitle(){
        return name;

    }

    public void setTitle(String name) {
        this.name = name;
    }

    public Set<BookModel> getBooks() {
        return books;
    }

    public void setBooks(Set<BookModel> books) {
        this.books = books;
    }


}
