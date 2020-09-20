package Ciphers;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public interface MessageEncryption {
    String encryptMessage(String message, File key) throws GeneralSecurityException, IOException;
    String decryptMessage(String encrypted, File key) throws GeneralSecurityException, IOException;
}
