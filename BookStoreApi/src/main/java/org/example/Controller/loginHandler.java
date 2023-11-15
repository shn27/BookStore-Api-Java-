package org.example.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.Auth.JWTtoken;
import org.example.Test.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

public class loginHandler implements HttpHandler {
    private static final int TokenLifeSpanMinutes = 10;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //System.out.println( "c'mon " + exchange.getRequestHeaders().get("Authorization").getLast()); //Basic YWRtaW46MTIzNA==
        //System.out.println( "d'mon " +exchange.getRequestHeaders().get("Authorization").get(0).split(" ")[1] );; //Basic YWRtaW46MTIzNA==

        loginHandler loginHandler = new loginHandler() ;


        String response = null;
        try {
            //creating a JWT token
            String Base64EncodedCredentials = exchange.getRequestHeaders().get("Authorization").get(0).split(" ")[1]; //todo done
            //String Base64EncodedCredentials = exchange.getRequestHeaders().get("Authorization").getFirst(); //todo done

            JWTtoken tmp = new JWTtoken(Base64EncodedCredentials, TokenLifeSpanMinutes);
            response = "JWT Token created successfully" ;
            loginHandler.SetCookiesExample(exchange ,  tmp.toString() ); // Token is passed through cookies rather response
         //   response = "Token Created Successfully" ;
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

        loginHandler loginHandler = new loginHandler() ;
        String tokenString = loginHandler.GetCookiesExample(t); // Token catched from cookies rather headers
        boolean valid = true;
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

    public void SetCookiesExample( HttpExchange exchange , String value ) {
        HttpCookie cookie = new HttpCookie("Token", value);
        cookie.setDomain("localhost");
        cookie.setPath("/");

        // Add the cookie to the response headers
        exchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
    }

    public String GetCookiesExample( HttpExchange exchange) {
        // Create a CookieManager
        Map<String, List<String>> headers = exchange.getRequestHeaders();
        List<String> cookieHeaders = headers.get("Cookie");

        StringBuffer token = new StringBuffer();

        // Process each cookie header
        if (cookieHeaders != null) {
            for (String cookieHeader : cookieHeaders) {
                List<HttpCookie> cookies = HttpCookie.parse(cookieHeader);

                // Process each cookie
                for (HttpCookie cookie : cookies) {
                    token.append(cookie.getValue()) ;
                }
            }
        }
        return token.toString() ;
    }
}
