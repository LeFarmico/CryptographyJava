import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class AESCipher implements MessageEncryption {

    @Override
    public byte[] encryptMessage(String message, String key) throws GeneralSecurityException {

            //Создание байт ключа на основе key
            byte[] rawKey = key.getBytes(StandardCharsets.UTF_8);
            if (rawKey.length != 16) {
                throw new IllegalArgumentException("Invalid key size.");
            }

        //Используем этот класс для создание итогового ключа на основе rawKey
        SecretKeySpec spec = new SecretKeySpec(rawKey, "AES");

        //Инициализируем шифр
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        //IvParameterSpec используется при CBC, DES, RSA шифровании
        cipher.init(Cipher.ENCRYPT_MODE, spec, new IvParameterSpec(new byte[key.length()]));
        return  cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));

    }

    @Override
    public String decryptMessage(byte[] encrypted, String key) throws GeneralSecurityException {
        byte[] rawKey = key.getBytes(StandardCharsets.UTF_8);

        //Используем этот класс для создание итогового ключа на основе rawKey
        SecretKeySpec spec =new SecretKeySpec(rawKey, "AES");

        //Инициализируем шифр
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, spec, new IvParameterSpec(new byte[16]));

        //Расшифровываем
        byte[] originalMessage = cipher.doFinal(encrypted);

        return new String(originalMessage, StandardCharsets.UTF_8);
    }
}
