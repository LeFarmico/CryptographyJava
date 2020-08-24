public class Loader {
    public static void main(String[] args) throws Exception {
        AESCipher cipher = new AESCipher();

        String text = "Something";
        String key = "some 16 symbols.";

        byte[] cipherBytes = cipher.encryptMessage(text, key);
        String originalMessage = cipher.decryptMessage(cipherBytes, key);

        System.out.println(text);
        System.out.println(cipherBytes);
        System.out.println(originalMessage);
    }
}
