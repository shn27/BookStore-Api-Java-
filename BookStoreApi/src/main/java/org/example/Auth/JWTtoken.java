package org.example.Auth;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

public class JWTtoken {
    private static final String SECRET_KEY = "abcdef";
    private static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
    private static final String encodedHeader = encode(JWT_HEADER.getBytes(StandardCharsets.UTF_8));
    private final String encodedPayload;
    private final JSONObject payload;
    private final String signature;
    public JWTtoken(String Base64EncodedCredentials, int MinutesTillExpiration) throws Exception {
        String username, password;
        try{
            String[] decoded = decode(Base64EncodedCredentials).split(":");
            username = decoded[0];
            password = decoded[1];
        }catch (Exception e){
            System.out.println("Invalid/Missing credentials: " + e);
            throw new RuntimeException();
        }

        payload = new JSONObject();
        payload.put("username", username);
        payload.put("password", password);
        long exp = LocalDateTime.now().plusMinutes(MinutesTillExpiration).toEpochSecond(ZoneOffset.UTC);
        payload.put("expiry_time", exp);

        encodedPayload = encode(payload.toString().getBytes(StandardCharsets.UTF_8));
        signature = HMACS256(encodedHeader + "." + encodedPayload);
    }
    public JWTtoken(String stringToken)throws Exception{
        String tokenList[] = stringToken.split("\\.");
        if(tokenList.length!=3 || !encodedHeader.equals(tokenList[0])) {
            throw new IllegalArgumentException("Provided token is invalid");
        }
        payload = new JSONObject(decode(tokenList[1]));

        encodedPayload = encode(payload.toString().getBytes(StandardCharsets.UTF_8));
        signature = tokenList[2];
    }

    public long remainingSeconds(){
        return payload.getLong("expiry_time") - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

    public boolean verify(){
        if(!payload.has("expiry_time"))return false;
        return (payload.getLong("expiry_time") > LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) && (signature.equals(HMACS256(encodedHeader + "." + encodedPayload)));
    }

    public String toString(){
        return encodedHeader + "." + encodedPayload + "." + signature;
    }

    private static String encode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

    private String HMACS256(String data) {
        try{
            byte[] hash = SECRET_KEY.getBytes(StandardCharsets.UTF_8);

            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
            sha256Hmac.init(secretKey);

            return encode(sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        }catch (Exception e){
            System.out.println("HMACS256 error: " + e);
            return "";
        }
    }

}
