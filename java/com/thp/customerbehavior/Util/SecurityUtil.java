package com.thp.customerbehavior.Util;

import java.util.Base64;

public class SecurityUtil {

    public static String Encode(String token) {
        String encodedString = Base64.getEncoder().encodeToString(token.getBytes());
        return encodedString;
    }

    public static String Decode(String token) {
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        return new String(decodedBytes);
    }

}
