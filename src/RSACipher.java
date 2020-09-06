import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSACipher implements MessageEncryption {

    KeyPairGenerator keyGen;
    KeyPair pair;
    PublicKey publicKey;
    PrivateKey privateKey;
    Cipher cipher = Cipher.getInstance("RSA");

    public RSACipher(int keyLength) throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keyLength);
    }
    public void generateKey(){
        this.pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    @Override
    public String encryptMessage(String message, String key) throws GeneralSecurityException {
        return null;
    }

    @Override
    public String decryptMessage(String encrypted, String key) throws GeneralSecurityException {
        return null;
    }
    public String encryptText(String msg, PrivateKey key)
            throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        return  Base64.getEncoder().encodeToString(cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8)));
    }

    public String decryptText(String msg, PublicKey key)
            throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(msg)));
    }
}
