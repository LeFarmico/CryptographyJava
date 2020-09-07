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
    Cipher cipher;

    public RSACipher(int keyLength) throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keyLength);
        this.cipher = Cipher.getInstance("RSA");
    }
    public void generateKey(){
        this.pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public String getPublicKey() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public String getPrivateKey() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    @Override
    public String encryptMessage(String message, String key)
            throws BadPaddingException, IllegalBlockSizeException,
            InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(key); //PRIVATE KEY!!!
        //Кодировка закрытого ключа
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(spec);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return  Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decryptMessage(String encrypted, String key)
            throws BadPaddingException, IllegalBlockSizeException,
            InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(key);//PUBLIC KEY!!!
        //Кодировка открытого ключа
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(spec);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }
}
