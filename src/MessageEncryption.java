import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MessageEncryption {
    byte[] encryptMessage(String message, String key) throws Exception;
    String decryptMessage(byte[] encrypted, String key) throws Exception;
}
