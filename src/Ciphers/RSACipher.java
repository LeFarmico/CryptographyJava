package Ciphers;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public String encryptMessage(String message, File publicKey)
            throws BadPaddingException, IllegalBlockSizeException,
            InvalidKeyException, InvalidKeySpecException {
        String pubKeyLine = "";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(publicKey.getPath()), StandardCharsets.UTF_8)){
              pubKeyLine += reader.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
             byte[] keyBytes = Base64.getDecoder().decode(pubKeyLine); //PUBLIC KEY!!!

             //Кодировка открытого ключа
             X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
             PublicKey key = keyFactory.generatePublic(spec);
             cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decryptMessage(String encrypted, File privateKey)
            throws BadPaddingException, IllegalBlockSizeException,
            InvalidKeyException, InvalidKeySpecException {

        String privateKeyLine = "";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(privateKey.getPath()), StandardCharsets.UTF_8)){
            privateKeyLine += reader.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }

        byte[] keyBytes = Base64.getDecoder().decode(privateKeyLine);//PRIVATE KEY!!!
        //Кодировка закрытого ключа
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        PrivateKey key = keyFactory.generatePrivate(spec);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }
}
