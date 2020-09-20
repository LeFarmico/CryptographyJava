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
        generate();
    }

    public void generate() {
        this.pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();

        Path privateKeyPath = Paths.get("src\\KeyStorage\\privateRSAKey.txt");
        Path publicKeyPath = Paths.get("src\\KeyStorage\\publicRSAKey.txt");

        writeKeyToFile(publicKeyPath, publicKey);
        writeKeyToFile(privateKeyPath, privateKey);
    }

    private void writeKeyToFile(Path pathToFile, Key key) {
        try (BufferedWriter writer = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)){
            writer.write(Base64.getEncoder().encodeToString(key.getEncoded()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
