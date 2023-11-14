package org.example.Interface;

import org.example.Model.Book;

import java.util.List;

public interface repository {
    public List<Book> getBooks() ;
    public Book save(Book book) ;
    public void deleteById(Long id) ;
    public Book findById(Long id) ;
    public boolean isPresent(Long id) ;
}
