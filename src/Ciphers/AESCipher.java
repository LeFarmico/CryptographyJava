package Ciphers;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESCipher implements MessageEncryption {

    Cipher cipher;
    SecretKeySpec spec;

    public AESCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    }

    @Override
    public String encryptMessage(String message, File keyFile) throws GeneralSecurityException {
        String secretKeyLine = changeToString(keyFile);
        byte[] rawKey = Base64.getDecoder().decode(secretKeyLine);
        //Используем этот класс для кодировки ключа на основе rawKey
        spec = new SecretKeySpec(rawKey, "AES");
        //IvParameterSpec используется при CBC, DES, RSA шифровании
        cipher.init(Cipher.ENCRYPT_MODE, spec, new IvParameterSpec(new byte[16]));
        //Кодируем указанный массив байтов в строку с использованием схемы кодирования Base64.
        return  Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decryptMessage(String encryptedMessage, File keyFile) throws GeneralSecurityException {
        String secretKeyLine = changeToString(keyFile);
        byte[] rawKey = Base64.getDecoder().decode(secretKeyLine);
        //Используем этот класс для кодировки ключа на основе rawKey
        spec = new SecretKeySpec(rawKey, "AES");
        cipher.init(Cipher.DECRYPT_MODE, spec, new IvParameterSpec(new byte[16]));
        //Декодируем указанный массив байтов в строку с использованием схемы кодирования Base64.
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
    }

    private String changeToString(File keyFile) {
        String secretKeyLine = "";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(keyFile.getPath()), StandardCharsets.UTF_8)) {
            secretKeyLine += reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return secretKeyLine;
    }
}
