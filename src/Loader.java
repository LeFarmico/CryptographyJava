import Ciphers.*;
import KeyGenerators.AESKeyType;
import KeyGenerators.RSAKeyType;
import KeyStorage.KeyStorage;

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

        KeyStorage keyStorage = new KeyStorage();

        MessageEncryption RSA = cipherFactory.getCipher(CipherType.RSA);
        String RSAEncrypted = RSA.encryptMessage(message, keyStorage.getPubKey());
        String RSADecrypted = RSA.decryptMessage(RSAEncrypted, keyStorage.getPrvKey());
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + RSAEncrypted);
        System.out.println("Decrypted message: " + RSADecrypted + "\n");

        MessageEncryption AES = cipherFactory.getCipher(CipherType.AES);
        String AESEncrypted = AES.encryptMessage(message, keyStorage.getSecKey());
        String AESDecrypted = AES.decryptMessage(AESEncrypted, keyStorage.getSecKey());
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + AESEncrypted);
        System.out.println("Decrypted message: " + AESDecrypted + "\n");

        MessageEncryption IMG = cipherFactory.getCipher(CipherType.IMG);
        String IMGEncrypted = IMG.encryptMessage(message, keyStorage.getImgKey());
        String IMGDecrypted = IMG.decryptMessage(IMGEncrypted, keyStorage.getImgKey());
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + IMGEncrypted);
        System.out.println("Decrypted message: " + IMGDecrypted + "\n");

    }

}
