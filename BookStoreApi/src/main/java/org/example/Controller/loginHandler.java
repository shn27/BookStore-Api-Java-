package org.example.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.Auth.JWTtoken;

import java.io.IOException;
import java.io.OutputStream;

public class loginHandler implements HttpHandler {
    private static final int TokenLifeSpanMinutes = 10;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //System.out.println( "c'mon " + exchange.getRequestHeaders().get("Authorization").getLast()); //Basic YWRtaW46MTIzNA==
        //System.out.println( "d'mon " +exchange.getRequestHeaders().get("Authorization").get(0).split(" ")[1] );; //Basic YWRtaW46MTIzNA==

        String response = null;
        try {
            String Base64EncodedCredentials = exchange.getRequestHeaders().get("Authorization").get(0).split(" ")[1]; //todo
            //String Base64EncodedCredentials = exchange.getRequestHeaders().get("Authorization").getFirst(); //todo

            JWTtoken tmp = new JWTtoken(Base64EncodedCredentials, TokenLifeSpanMinutes);
            response = tmp.toString();
        } catch (Exception e) {
            System.out.println("JWT token creation error: " + e);
            throw new RuntimeException(e);
        }

        exchange.sendResponseHeaders(200, response.length());

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

        exchange.close();
    }

    private static String validateAndGetToken(String s) {
        String slist[] = s.split(" ");
        if (slist.length != 2 || !slist[0].equals("Bearer")) return "";
        return slist[1];
    }

    public static boolean isAuthenticated(HttpExchange t) throws IOException {
        boolean valid = true;
        var keys = t.getRequestHeaders();

       // System.out.println( "amar bhul " + keys.get("Authorization").getFirst());

        if (!keys.containsKey("Authorization")) valid = false;

        String tokenString = "";
        if (valid){
            tokenString = validateAndGetToken(keys.get("Authorization").get(0));//TODO
          //  tokenString = validateAndGetToken(keys.get("Authorization").getFirst() );//TODO
        }

        if (tokenString.isEmpty()) valid = false;

        if (valid) {
            try {
                JWTtoken tmpToken = new JWTtoken(tokenString);
                valid = tmpToken.verify();
                if (valid) {
                    long seconds = tmpToken.remainingSeconds();
                    System.out.println("TOKEN VALIDITY: " + seconds / 60 + " minutes and " + seconds % 60 + " seconds remaining");
                }
            } catch (Exception e) {
                System.out.println("JWT token authentication error: " + e);
                valid = false;
            }
        }

        if (!valid) {
            String response = "This action is unauthorized\nAuthorization token missing/expired";
            t.sendResponseHeaders(403, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            t.close();
        }

        return valid;
    }
}
