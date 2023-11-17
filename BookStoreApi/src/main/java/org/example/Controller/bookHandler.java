package org.example.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.Model.Book;
import org.example.repositories.BookRepository;
import org.example.services.BookService;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;

import static java.lang.Long.parseLong;
import static org.example.Controller.loginHandler.*;

public class bookHandler implements HttpHandler {
    BookService bookService = new BookService() ;
    Book book = new Book() ;

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if(!isAuthenticated(exchange)){
            return;
        }

        StringBuffer response = new StringBuffer();
        String method = exchange.getRequestMethod();
        Integer responseHeader = 200 ;

        if(method.equals("GET")){
            URI uri = exchange.getRequestURI();
            String query = uri.getRawQuery(); // uri er question er por ja ce

            if(query == null){
              response.append(bookService.getBooks()) ;
            }
            else{
                Long bookId = parseLong(query) ;
               if(bookService.isPresent(bookId)) response.append(bookService.getBook(bookId).toString())  ;
               else {
                   response.append("Sorry Book is not present");
                   responseHeader = 204 ;//No content
               }
            }
        }
        else if(method.equals("POST")){
            URI uri = exchange.getRequestURI();
            if(uri.toString().equals("/bookStore/logout") ){
                SetCookieNull(exchange) ;
            }
            else if(!uri.toString().equals("/bookStore/addBook") ){
                response.append("URL is not correct") ; responseHeader = 400 ;//Bad Request
            }
            else{
                JSONObject jsonObject = giveJsonObject(exchange) ;
// json todo
                if( ! (jsonObject.has("id") && jsonObject.has("name") && jsonObject.has("author") && jsonObject.has("genre") ) ){
                    response.append("key Missing") ;responseHeader = 400 ;//Bad Request
                }
                else{
                    Long id =   jsonObject.getLong("id") ;
                    String  name = jsonObject.getString("name") ,
                            author = jsonObject.getString("author") ,
                            genre = jsonObject.getString("genre");

                    response.append(bookService.saveBook(new Book(id , name , author , genre)))   ;
                }
            }
        }
        else if(method.equals("DELETE")){

            URI uri = exchange.getRequestURI();
            if(!uri.toString().startsWith("/bookStore/deleteBook?") ){
                response.append("URL is not correct") ;responseHeader = 400 ;//Bad Request
            }
            else if(uri.getRawQuery().length() == 0){
                response.append("URL is not correct.Please add book id you want to delete") ;responseHeader = 400 ;//Bad Request
            }
            else {
                String query = uri.getRawQuery();
                Long bookId = parseLong(query) ;



                if(bookService.isPresent(bookId)){
                    bookService.deleteBook(bookId);
                    response.append("Book Deleted successfully") ;
                }
                else {
                    response.append("Book id is not present") ;responseHeader = 404 ;//Not Found
                }
            }
        }
        else if(method.equals("PUT")){

            URI uri = exchange.getRequestURI();
            if(!uri.toString().startsWith("/bookStore/updateBook?") ){
                response.append("URL is not correct") ;responseHeader = 400 ;//Bad Request
            }
            else if(uri.getRawQuery().length() == 0){
                response.append("URL is not correct") ;responseHeader = 400 ;//Bad Request
            }
            else{
                String query = uri.getRawQuery(); // uri er question er por ja ce
                Long oldId = parseLong(query) ;

                JSONObject jsonObject = giveJsonObject(exchange) ;
// json todo
                if( ! (jsonObject.has("id") && jsonObject.has("name") && jsonObject.has("author") && jsonObject.has("genre") ) ){
                    response.append("key Missing") ;responseHeader = 400 ;//Bad Request
                }
                else{
                    Long id =   jsonObject.getLong("id") ;
                    String  name = jsonObject.getString("name") ,
                            author = jsonObject.getString("author") ,
                            genre = jsonObject.getString("genre");

                    if(bookService.isPresent(oldId)){
                        bookService.deleteBook(oldId);
                        response.append("Book Updated\n") ;
                        response.append(bookService.saveBook(new Book( id, name , author , genre)))   ;
                    }
                    else {
                        response.append("Book id is not present") ;responseHeader = 404 ;//Not Found
                    }
                }
            }
        }

        //todo done
        exchange.sendResponseHeaders(responseHeader, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }

    private JSONObject giveJsonObject(HttpExchange exchange) throws IOException {
        BufferedReader httpInput = new BufferedReader(new InputStreamReader(
                exchange.getRequestBody(), "UTF-8"));
        StringBuilder in = new StringBuilder();
        String input;
        while ((input = httpInput.readLine()) != null) {
            in.append(input).append(" ");
        }
        httpInput.close();
        String jsonString = String.valueOf(in.toString());
        JSONObject jsonObject = new JSONObject(jsonString) ;
        return jsonObject ;
    }
}
