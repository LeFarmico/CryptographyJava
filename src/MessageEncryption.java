
import java.security.GeneralSecurityException;

public interface MessageEncryption {
    String encryptMessage(String message, String key) throws GeneralSecurityException;
    String decryptMessage(String encrypted, String key) throws GeneralSecurityException;
}
