import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Loader {
    public static void main(String[] args) throws Exception {
        AESCipher cipher = new AESCipher();

        String key = "myNameIsArtyomOk";
        String message = "тот кого нельзя называть, вернулся и он очень очень зол";
//
//        String encrypted = cipher.encryptMessage(message, key);
//        String originalMessage = cipher.decryptMessage(encrypted, key);
//
//        System.out.println(message);
//        System.out.println(encrypted);
//        System.out.println(originalMessage);

        RSACipher RSA = new RSACipher(1024);
        RSA.generateKey();
        String encrypted = RSA.encryptText(message, RSA.getPrivateKey());
        System.out.println(encrypted);
        System.out.println(RSA.decryptText(encrypted, RSA.getPublicKey()));
    }

}
