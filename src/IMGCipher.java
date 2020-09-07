import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class IMGCipher implements MessageEncryption{

    File file;
    String keyString;

    public IMGCipher(File file) {
        this.file = file;
    }

    public void generateKey() throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        byte[] rawKey = inputStream.readAllBytes();
        keyString = Base64.getEncoder().encodeToString(rawKey);
    }

    @Override
    public String encryptMessage(String message, String key) throws GeneralSecurityException {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        byte[] msgBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = new byte[msgBytes.length];
        for (int i = 0; i < msgBytes.length; i++) {
            encryptedBytes[i] = (byte) (msgBytes[i] ^ keyBytes[i]);
        }
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    @Override
    public String decryptMessage(String encrypted, String key) throws GeneralSecurityException {
        byte[] encryptedBytes = Base64.getDecoder().decode(encrypted);
        byte[] keyBytes = Base64.getDecoder().decode(key);
        byte[] originalBytes = new byte[encryptedBytes.length];
        for (int i = 0; i < encryptedBytes.length; i++) {
            originalBytes[i] = (byte) (encryptedBytes[i] ^ keyBytes[i]);
        }
        return new String(originalBytes);
    }
}
