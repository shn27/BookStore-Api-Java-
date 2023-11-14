package org.example.Interface;

import org.example.Model.Book;

import java.util.List;

public interface service {
    public StringBuffer getBooks() ;
    public StringBuffer getBook(Long id) ;
    public void deleteBook(Long id) ;
    public StringBuffer saveBook(Book book) ;
    public boolean isPresent(Long id) ;
}
