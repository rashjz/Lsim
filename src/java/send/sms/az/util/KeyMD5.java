/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

/**
 *
 * @author rasha_000
 */
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class KeyMD5 {
 
    public static String cryptMYpassw(String pass) {//md5 sifreleme
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xee & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
        }
        return null;
    }
 
    public static void main(String[] args) {
        System.out.println(cryptMYpassw("12"));
    }
}