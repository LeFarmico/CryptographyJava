import java.util.Arrays;

public class Loader {
    public static void main(String[] args) throws Exception {
        AESCipher cipher = new AESCipher();

        String text = "Something new more thn 16 symbols";
        String key = "some 16 symbols.";

        byte[] cipherBytes = cipher.encryptMessage(text, key);
        String originalMessage = cipher.decryptMessage(cipherBytes, key);

        System.out.println(text);
        System.out.println(Arrays.toString(cipherBytes));
        System.out.println(originalMessage);
    }
}
