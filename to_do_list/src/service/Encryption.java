package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Encryption {
    public static String cipher(String pass) {
        String cipher = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] bytes = md5.digest(pass.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for(byte b : bytes){
                stringBuilder.append(String.format("%02X",b));
            }
            cipher = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return cipher;
    }
}
