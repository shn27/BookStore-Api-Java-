package org.example.services;

import org.example.Model.Book;
import org.example.repositories.BookRepository;

import java.util.HashMap;
import java.util.List;

public class BookService   {
    BookRepository books = new BookRepository();

    public BookService() {
    }
    public StringBuffer getBooks() {
        HashMap<Long , Book> bookList = books.getBooks();
        StringBuffer stringBuffer = new StringBuffer() ;
        for(Book book: bookList.values()){//todo
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
        if(books.isPresent(book.getId())){
            StringBuffer s = new StringBuffer() ;
            s.append("This Id is not available. You can use id ") ;
            s.append(books.getAvailableId().toString()) ;
            return  s ;
        }
        books.save(book);
        return new StringBuffer(book.toString()) ;
    }
    public boolean isPresent(Long id) {
        return books.isPresent(id);
    }
}
