import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AESCipher implements MessageEncryption {

    SecretKeySpec spec;
    MessageDigest sha;
    Cipher cipher;
    String keyString;

    public AESCipher(String keyString) throws NoSuchAlgorithmException, NoSuchPaddingException {
        //Инициализируем шифр
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //MessageDigest будет проверять ключ на целостность
        this.sha = MessageDigest.getInstance("SHA-256");
        this.keyString = keyString;
    }

    public void generateKey(){
        //Создание байт ключа на основе key
        byte[] rawKey = keyString.getBytes(StandardCharsets.UTF_8);
        if (rawKey.length != 16) {
            throw new IllegalArgumentException("Invalid key size. " + rawKey.length);
        }
        //Вычисляем дайджест
        rawKey = sha.digest(rawKey);
        rawKey = Arrays.copyOf(rawKey, 16);
        //Используем этот класс для кодировки ключа на основе rawKey
        spec = new SecretKeySpec(rawKey, "AES");
    }

    @Override
    public String encryptMessage(String message, String key) throws GeneralSecurityException {
        //setKey();
        //IvParameterSpec используется при CBC, DES, RSA шифровании
        cipher.init(Cipher.ENCRYPT_MODE, spec, new IvParameterSpec(new byte[16]));
        //Кодируем указанный массив байтов в строку с использованием схемы кодирования Base64.
        return  Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decryptMessage(String encryptedMessage, String key) throws GeneralSecurityException {
        //setKey();
        cipher.init(Cipher.DECRYPT_MODE, spec, new IvParameterSpec(new byte[16]));
        //Декодируем указанный массив байтов в строку с использованием схемы кодирования Base64.
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
    }
}
