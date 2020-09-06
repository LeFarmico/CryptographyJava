import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AESCipher implements MessageEncryption {

    private static SecretKeySpec spec;

    public static void setKey(String keyString) throws NoSuchAlgorithmException {

        //Создание байт ключа на основе key
        byte[] rawKey = keyString.getBytes(StandardCharsets.UTF_8);
        if (rawKey.length != 16) {
            throw new IllegalArgumentException("Invalid key size.");
        }

        //MessageDigest будет проверять ключ на целостность
        MessageDigest sha = MessageDigest.getInstance("SHA-256");

        //Вычисляем дайджест
        rawKey = sha.digest(rawKey);
        rawKey = Arrays.copyOf(rawKey, 16);

        //Используем этот класс для кодировки ключа на основе rawKey
        spec = new SecretKeySpec(rawKey, "AES");
    }

    @Override
    public String encryptMessage(String message, String key) throws GeneralSecurityException {

        String[] messageParts = message.split("(?<=\\G.{16})");

        setKey(key);

        //Инициализируем шифр
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        //IvParameterSpec используется при CBC, DES, RSA шифровании
        cipher.init(Cipher.ENCRYPT_MODE, spec, new IvParameterSpec(new byte[16]));

        //Кодируем указанный массив байтов в строку с использованием схемы кодирования Base64.
        return  Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decryptMessage(String encryptedMessage, String key) throws GeneralSecurityException {

        setKey(key);

        //Инициализируем шифр
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, spec, new IvParameterSpec(new byte[16]));

        //Декодируем указанный массив байтов в строку с использованием схемы кодирования Base64.
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
    }
}
