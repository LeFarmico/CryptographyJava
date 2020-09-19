package Ciphers;

import KeyGenerators.AESKeyType;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESCipher implements MessageEncryption {

    Cipher cipher;
    SecretKeySpec spec;

    public AESCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        //Инициализируем шифр
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    }

    @Override
    public String encryptMessage(String message, String key) throws GeneralSecurityException {
        byte[] rawKey = Base64.getDecoder().decode(key);
        //Используем этот класс для кодировки ключа на основе rawKey
        spec = new SecretKeySpec(rawKey, "AES");
        //IvParameterSpec используется при CBC, DES, RSA шифровании
        cipher.init(Cipher.ENCRYPT_MODE, spec, new IvParameterSpec(new byte[16]));
        //Кодируем указанный массив байтов в строку с использованием схемы кодирования Base64.
        return  Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decryptMessage(String encryptedMessage, String key) throws GeneralSecurityException {
        byte[] rawKey = Base64.getDecoder().decode(key);
        //Используем этот класс для кодировки ключа на основе rawKey
        spec = new SecretKeySpec(rawKey, "AES");
        cipher.init(Cipher.DECRYPT_MODE, spec, new IvParameterSpec(new byte[16]));
        //Декодируем указанный массив байтов в строку с использованием схемы кодирования Base64.
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
    }
}
