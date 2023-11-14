package org.example.services;

import org.example.Interface.service;
import org.example.Model.Book;
import org.example.repositories.BookRepository;

import java.util.List;

public class BookService implements service {
    BookRepository books = new BookRepository();

    public BookService() {
    }
    public StringBuffer getBooks() {
        List<Book> bookList = books.getBooks();
        StringBuffer stringBuffer = new StringBuffer() ;
        for(Book book: bookList){
            stringBuffer.append(book.toString()) ;
        }
        return stringBuffer ;
    }
    public StringBuffer getBook(Long id) {
        StringBuffer book = new StringBuffer(books.findById(id).toString()) ;
        return book ;
    }

    public void deleteBook(Long id) {
        books.deleteById(id);
    }
    public StringBuffer saveBook(Book book) {
        books.save(book);
        return new StringBuffer(book.toString()) ;
    }

    @Override
    public boolean isPresent(Long id) {
        return books.isPresent(id);
    }
}
