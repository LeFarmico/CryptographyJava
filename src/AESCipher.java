import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.*;

public class AESCipher implements MessageEncryption {

    @Override
    public byte[] encryptMessage(String message, String key) throws Exception {

        //Создание байт ключа на основе key
        byte[] rawKey = key.getBytes(Charset.forName("UTF-8"));

        //Используем этот класс для создание итогового ключа на основе rawKey
        SecretKeySpec spec = new SecretKeySpec(rawKey, "AES");

        //Инициализируем шифр
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        //IvParameterSpec используется при CBC, DES, RSA шифровании
        cipher.init(Cipher.ENCRYPT_MODE, spec, new IvParameterSpec(new byte[key.length()]));
        return  cipher.doFinal(message.getBytes(Charset.forName("UTF-8")));

    }

    @Override
    public String decryptMessage(byte[] encrypted, String key) throws Exception {
        byte[] rawKey = key.getBytes(Charset.forName("UTF-8"));

        //Используем этот класс для создание итогового ключа на основе rawKey
        SecretKeySpec spec =new SecretKeySpec(rawKey, "AES");

        //Инициализируем шифр
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, spec, new IvParameterSpec(new byte[16]));

        //Расшифровываем
        byte[] originalMessage = cipher.doFinal(encrypted);

        return new String(originalMessage, Charset.forName("UTF-8"));
    }
}
