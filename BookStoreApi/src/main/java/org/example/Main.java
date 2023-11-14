package org.example;

import org.example.Controller.Controller;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        try {
            controller.controll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
Almost done.
If want to addBook but does not give all the headers  -- solved
If want to updateBook but does not give everything    -- solved
If want to get or delete or update a book but not found -- If id is not there then id will not be deleted
 */
