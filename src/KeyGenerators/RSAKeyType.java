package KeyGenerators;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.util.Base64;

public class RSAKeyType {

    KeyPairGenerator keyGen;
    KeyPair pair;
    PublicKey publicKey;
    PrivateKey privateKey;

    public RSAKeyType(int keyLength) throws NoSuchAlgorithmException{
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keyLength);
        generateKey();
    }

    public void generateKey() {
        this.pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();

        Path publicKeyPath = Paths.get("src\\publicRSAKey.txt");
        Path privateKeyPath = Paths.get("src\\privateRSAKey.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(publicKeyPath, StandardCharsets.UTF_8)){
            writer.write(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = Files.newBufferedWriter(privateKeyPath, StandardCharsets.UTF_8)){
            writer.write(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
