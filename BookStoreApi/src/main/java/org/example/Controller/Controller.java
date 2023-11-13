package org.example.Controller;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Controller {
    public Controller() {
    }
    public void controll() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress( 8000), 0);
        server.createContext("/BookStore", new bookHandler());

        server.start();
    }
}
