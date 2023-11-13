package org.example.Interface;

import org.example.Model.Book;

import java.util.List;

public interface service {
    public List<Book> getBooks() ;
    public Book getBook(Long id) ;
    public void deleteBook(Long id) ;
    public Book saveBook(Book book) ;
}
