import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MessageEncryption {
    byte[] encryptMessage(String message, String key) throws GeneralSecurityException;
    String decryptMessage(byte[] encrypted, String key) throws GeneralSecurityException;
}
