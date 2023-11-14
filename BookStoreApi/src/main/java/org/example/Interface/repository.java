package org.example.Interface;

import org.example.Model.Book;

import java.util.HashMap;
import java.util.List;

public interface repository {
    public HashMap<Long , Book> getBooks() ;
    public Book save(Book book) ;
    public void deleteById(Long id) ;
    public Book findById(Long id) ;
    public boolean isPresent(Long id) ;

    public Long getAvailableId( ) ;


}
