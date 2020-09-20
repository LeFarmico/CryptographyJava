import Ciphers.*;
import KeyGenerators.AESKeyType;
import KeyGenerators.RSAKeyType;

import java.io.File;

public class Loader {
    public static void main(String[] args) throws Exception {

//        String[] messageParts = message.split("(?<=\\G.{16})");

        String key = "abcdefghijklmnop";
        String message = "тот кого нельзя называть, вернулся и он очень очень зол";
        CipherFactory cipherFactory = new CipherFactory();

        AESKeyType AESKey = new AESKeyType(key);
        RSAKeyType RSAKey = new RSAKeyType(1024);
        AESKey.generate();
        RSAKey.generate();

        File pubKey = new File("src/KeyStorage/publicRSAKey.txt");
        File prvKey = new File("src/KeyStorage/privateRSAKey.txt");
        File secKey = new File("src/KeyStorage/secretAESKey.txt");
        File imgKey = new File("src/KeyStorage/Image_key.png");

        MessageEncryption RSA = cipherFactory.getCipher(CipherType.RSA);
        String RSAEncrypted = RSA.encryptMessage(message, pubKey);
        String RSADecrypted = RSA.decryptMessage(RSAEncrypted, prvKey);
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + RSAEncrypted);
        System.out.println("Decrypted message: " + RSADecrypted + "\n");

        MessageEncryption AES = cipherFactory.getCipher(CipherType.AES);
        String AESEncrypted = AES.encryptMessage(message, secKey);
        String AESDecrypted = AES.decryptMessage(AESEncrypted, secKey);
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + AESEncrypted);
        System.out.println("Decrypted message: " + AESDecrypted + "\n");

        MessageEncryption IMG = cipherFactory.getCipher(CipherType.IMG);
        String IMGEncrypted = IMG.encryptMessage(message, imgKey);
        String IMGDecrypted = IMG.decryptMessage(IMGEncrypted, imgKey);
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + IMGEncrypted);
        System.out.println("Decrypted message: " + IMGDecrypted + "\n");

    }

}
