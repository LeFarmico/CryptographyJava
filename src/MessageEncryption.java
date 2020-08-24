
import java.security.GeneralSecurityException;

public interface MessageEncryption {
    byte[] encryptMessage(String message, String key) throws GeneralSecurityException;
    String decryptMessage(byte[] encrypted, String key) throws GeneralSecurityException;
}
