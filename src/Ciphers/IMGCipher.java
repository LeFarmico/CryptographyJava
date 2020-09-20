package Ciphers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class IMGCipher implements MessageEncryption {

    @Override
    public String encryptMessage(String message, File key) throws GeneralSecurityException, IOException {
        FileInputStream inputStream = new FileInputStream(key);
        byte[] keyBytes = inputStream.readAllBytes();
        byte[] msgBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = new byte[msgBytes.length];
        for (int i = 0; i < msgBytes.length; i++) {
            encryptedBytes[i] = (byte) (msgBytes[i] ^ keyBytes[i]);
        }
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    @Override
    public String decryptMessage(String encrypted, File key) throws GeneralSecurityException, IOException {
        byte[] encryptedBytes = Base64.getDecoder().decode(encrypted);
        FileInputStream inputStream = new FileInputStream(key);
        byte[] keyBytes = inputStream.readAllBytes();
        byte[] originalBytes = new byte[encryptedBytes.length];
        for (int i = 0; i < encryptedBytes.length; i++) {
            originalBytes[i] = (byte) (encryptedBytes[i] ^ keyBytes[i]);
        }
        return new String(originalBytes);
    }
}
