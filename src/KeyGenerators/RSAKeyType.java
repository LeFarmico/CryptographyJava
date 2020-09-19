package KeyGenerators;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

public class RSAKeyType {

    KeyPairGenerator keyGen;
    KeyPair pair;
    PublicKey publicKey;
    PrivateKey privateKey;

    public RSAKeyType(int keyLength) throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keyLength);
        generateKey();
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
}
