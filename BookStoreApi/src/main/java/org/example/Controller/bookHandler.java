package org.example.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.Model.Book;
import org.example.repositories.BookRepository;
import org.example.services.BookService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import static java.lang.Long.parseLong;
import static org.example.Controller.loginHandler.isAuthenticated;

public class bookHandler implements HttpHandler {
    BookService bookService = new BookService() ;
    Book book = new Book() ;

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if(!isAuthenticated(exchange)){
            System.out.println("Need auth");
            return;
        }

        StringBuffer response = new StringBuffer();
        String method = exchange.getRequestMethod();

        if(method.equals("GET")){
            URI uri = exchange.getRequestURI();
            String query = uri.getRawQuery(); // uri er question er por ja ce

            if(query == null){
              response.append(bookService.getBooks()) ;
            }
            else{
                Long bookId = parseLong(query.substring(query.indexOf("=")+1)) ;
                response.append(bookService.getBook(bookId).toString())  ;
            }
        }
        else if(method.equals("POST")){
            URI uri = exchange.getRequestURI();
            if(!uri.toString().equals("/BookStore/addBook") ){
                response.append("URL is not correct") ;
            }
            else{
                var headers = exchange.getRequestHeaders() ;

                if( ! (headers.containsKey("id") && headers.containsKey("name") && headers.containsKey("author") && headers.containsKey("genre") ) ){
                    response.append("Headers Missing") ;
                }
                else{
                    Long id = parseLong(headers.get("id").get(0));
                    String  name = headers.get("name").get(0) ,
                            author = headers.get("author").get(0) ,
                            genre = headers.get("genre").get(0) ;

                    response.append(bookService.saveBook(new Book(id , name , author , genre)))   ;
                }
            }
        }
        else if(method.equals("DELETE")){

            URI uri = exchange.getRequestURI();
            if(!uri.toString().startsWith("/BookStore/deleteBook?") ){
                response.append("URL is not correct") ;
            }
            else if(uri.getRawQuery().length() == 0)response.append("URL is not correct.Please add book id you want to delete") ;
            else {
                String query = uri.getRawQuery();
                Long bookId = parseLong(query.substring(query.indexOf("=")+1)) ;
                bookService.deleteBook(bookId);
            }
        }
        else if(method.equals("PUT")){

            URI uri = exchange.getRequestURI();
            if(!uri.toString().startsWith("/BookStore/updateBook?") ){
                response.append("URL is not correct") ;
            }
            else if(uri.getRawQuery().length() == 0)response.append("URL is not correct") ;
            else{
                String query = uri.getRawQuery(); // uri er question er por ja ce
                Long oldId = parseLong(query) ;
                var headers = exchange.getRequestHeaders() ;

                if( ! (headers.containsKey("id") && headers.containsKey("name") && headers.containsKey("author") && headers.containsKey("genre") ) ){
                    response.append("Headers Missing") ;
                }
                else{
                    Long id = parseLong(headers.get("id").get(0));
                    String  name = headers.get("name").get(0) ,
                            author = headers.get("author").get(0) ,
                            genre = headers.get("genre").get(0) ;

                    bookService.deleteBook(oldId);
                    response.append(bookService.saveBook(new Book( id, name , author , genre)))   ;
                }
            }
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
