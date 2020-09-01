import java.util.Arrays;

public class Loader {
    public static void main(String[] args) throws Exception {
        AESCipher cipher = new AESCipher();

        String key = "myNameIsArtyomOk";
        String message = "Тот кого нельзя называть, вернулся";

        String cipherString = cipher.encryptMessage(message, key);
        String originalMessage = cipher.decryptMessage(cipherString, key);

        System.out.println(message);
        System.out.println(cipherString);
        System.out.println(originalMessage);


    }

}
