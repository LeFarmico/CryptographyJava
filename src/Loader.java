import KeyGenerators.AESKeyType;
import KeyGenerators.RSAKeyType;

import java.util.Base64;

public class Loader {
    public static void main(String[] args) throws Exception {

//        String[] messageParts = message.split("(?<=\\G.{16})");

        String key = "abcdefghijklmnop";
        String message = "тот кого нельзя называть, вернулся и он очень очень зол";
        CipherFactory factory = new CipherFactory();
        AESKeyType AESKey = new AESKeyType(key);
        RSAKeyType RSAKey = new RSAKeyType(1024);

//        Ciphers.MessageEncryption RSA = factory.getCipher(CipherType.RSA);
//        String RSAEncrypted = RSA.encryptMessage(message, RSAKey.getPublicKey());
//        String RSADecrypted = RSA.decryptMessage(RSAEncrypted, RSAKey.getPrivateKey());
//        System.out.println("Original message: " + message);
//        System.out.println("Encrypted message: " + RSAEncrypted);
//        System.out.println("Decrypted message: " + RSADecrypted + "\n");

//        Ciphers.MessageEncryption AES = factory.getCipher(CipherType.AES);
//        String AESEncrypted = AES.encryptMessage(message, AESKey.generateKey());
//        String AESDecrypted = AES.decryptMessage(AESEncrypted, AESKey.generateKey());
//        System.out.println("Original message: " + message);
//        System.out.println("Encrypted message: " + AESEncrypted);
//        System.out.println("Decrypted message: " + AESDecrypted + "\n");

//        Ciphers.MessageEncryption IMG = factory.getCipher(CipherType.IMG);
//        String IMGEncrypted = IMG.encryptMessage(message, IMG.keyString);
//        String IMGDecrypted = IMG.decryptMessage(IMGEncrypted, IMG.keyString);
//        System.out.println("Original message: " + message);
//        System.out.println("Encrypted message: " + IMGEncrypted);
//        System.out.println("Decrypted message: " + IMGDecrypted + "\n");

    }

}
