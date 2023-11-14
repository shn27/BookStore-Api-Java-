package org.example.repositories;

import org.example.Interface.repository;
import org.example.Model.Book;

import java.util.*;

public class BookRepository implements repository {
    static List<Book> books = new ArrayList<>() ;
    static Set<Long> availableId = new HashSet<Long>() ;

    static{
        Long id = 1L ;
        while(id <= 10000L){
            availableId.add(id) ;id++ ;
        }
    }

    static HashMap<Long , Book> books1 = new HashMap<Long , Book>() ;
    public BookRepository( ) {
    }

    @Override
    public HashMap<Long , Book>  getBooks() {
        return books1;
    }
    @Override
    public Book save(Book book) {
        books1.put(book.getId() , book) ;
        availableId.remove(book.getId()) ;
        return book ;
    }

    @Override
    public void deleteById(Long id) {
        books1.remove(id) ;
        availableId.add(id) ;
    }

    @Override
    public Book findById(Long id) {
       return books1.get(id) ;
    }
    @Override
    public boolean isPresent(Long id) {
        if(books1.containsKey(id))return true ;
        return false ;
    }

    @Override
    public Long getAvailableId( ) {
        Iterator<Long> iterator = availableId.iterator();
        if(iterator.hasNext()) return (Long) iterator.next();
        return null;
    }
}
