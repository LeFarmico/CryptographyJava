import java.io.File;

public class Loader {
    public static void main(String[] args) throws Exception {

//        String[] messageParts = message.split("(?<=\\G.{16})");

        String key = "Долго я э";
        String message = "тот кого нельзя называть, вернулся и он очень очень зол";

        RSACipher RSA = new RSACipher(1024);
        RSA.generateKey();
        String RSAEncrypted = RSA.encryptMessage(message, RSA.getPrivateKey());
        String RSADecrypted = RSA.decryptMessage(RSAEncrypted, RSA.getPublicKey());
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + RSAEncrypted);
        System.out.println("Decrypted message: " + RSADecrypted + "\n");

        AESCipher AES = new AESCipher(key);
        AES.generateKey();
        String AESEncrypted = AES.encryptMessage(message, key);
        String AESDecrypted = AES.decryptMessage(AESEncrypted, key);
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + AESEncrypted);
        System.out.println("Decrypted message: " + AESDecrypted + "\n");

        File dir = new File("src/Image_key.png");
        IMGCipher IMG = new IMGCipher(dir);
        IMG.generateKey();
        String IMGEncrypted = IMG.encryptMessage(message, IMG.keyString);
        String IMGDecrypted = IMG.decryptMessage(IMGEncrypted, IMG.keyString);
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + IMGEncrypted);
        System.out.println("Decrypted message: " + IMGDecrypted + "\n");
    }

}
