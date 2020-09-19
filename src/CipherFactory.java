import Ciphers.AESCipher;
import Ciphers.IMGCipher;
import Ciphers.MessageEncryption;
import Ciphers.RSACipher;

import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CipherFactory {

    public MessageEncryption getCipher(CipherType cipherType)
            throws NoSuchPaddingException, NoSuchAlgorithmException, IOException {
        if (cipherType == null)
            throw new NullPointerException();
        else if (cipherType.equals(CipherType.AES)){
            return new AESCipher();
        }
        else if (cipherType.equals(CipherType.RSA)){
            return  new RSACipher();
        }
        else if (cipherType.equals(CipherType.IMG)){
            return new IMGCipher(new File("src/Image_key.png"));
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
