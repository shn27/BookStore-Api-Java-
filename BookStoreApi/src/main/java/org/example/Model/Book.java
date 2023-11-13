package org.example.Model;

public class Book {
    private Long id;
    private String name;
    private String author;
    private String genre;
    public Book() {
    }
    public Book(Long id, String name, String author, String genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "\nBook ID  \""+ this.id+ "\"" + "\nBook Name   \"" + this.name + "\"\nAuthor Name  \"" + this.author + "\"\nGenre  \"" + this.genre + "\"" ;
    }
}
