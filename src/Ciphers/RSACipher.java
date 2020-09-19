package Ciphers;

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

    KeyFactory keyFactory;
    Cipher cipher;

    public RSACipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("RSA");
        this.keyFactory = KeyFactory.getInstance("RSA");
    }

    @Override
    public String encryptMessage(String message, String publicKey)
            throws BadPaddingException, IllegalBlockSizeException,
            InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey); //PUBLIC KEY!!!
        //Кодировка открытого ключа
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        PublicKey key = keyFactory.generatePublic(spec);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return  Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decryptMessage(String encrypted, String privateKey)
            throws BadPaddingException, IllegalBlockSizeException,
            InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);//PRIVATE KEY!!!
        //Кодировка закрытого ключа
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        PrivateKey key = keyFactory.generatePrivate(spec);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }
}
