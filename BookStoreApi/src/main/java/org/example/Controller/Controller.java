package org.example.Controller;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Controller {


    private static String my_username = "admin";
    private static String my_password = "1234";

    public Controller() {
    }
    public void controll() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress( 8000), 0);
        server.createContext("/bookStore", new bookHandler()); //todo
        HttpContext _login = server.createContext("/bookStore/login", new loginHandler());
        _login.setAuthenticator(new BasicAuthenticator("realm") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals(my_username) && pwd.equals(my_password);
            }
        });

        server.start();
    }
}
