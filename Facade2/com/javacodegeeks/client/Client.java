package com.javacodegeeks.client;
import com.javacodegeeks.facade.EncryptorFacade;
import static com.javacodegeeks.facade.EncryptorFacade.EncryptionType.*;
public class Client {
    public static void main(String[] args) {
        String myText = "Encrypt this text";
        System.out.println("Text to be encrypted: " + myText);
        EncryptorFacade e= new EncryptorFacade();
        System.out.println("MD5 encryption");
        System.out.println(e.encrypt(MD5, myText));
        System.out.println("MD5 salted encryption");
        System.out.println(e.encrypt(MD5Salted, myText));
        System.out.println("SHA encryption");
        System.out.println(e.encrypt(SHA, myText));
        System.out.println("SHA salted encryption");
        System.out.println(e.encrypt(SHASalted, myText));
        System.out.println("SHA256 encryption");
        System.out.println(e.encrypt(SHA256, myText));
        System.out.println("SHA256Salted encryption");
        System.out.println(e.encrypt(SHA256Salted, myText));
    }
}
