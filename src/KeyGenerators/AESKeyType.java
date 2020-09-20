package KeyGenerators;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AESKeyType {

    String sixteenSymbolsKey;
    MessageDigest sha;
    SecretKey secretKey;

    public AESKeyType(String sixteenSymbolsKey) throws NoSuchAlgorithmException {
        this.sixteenSymbolsKey = sixteenSymbolsKey;
        //MessageDigest будет проверять ключ на целостность
        this.sha = MessageDigest.getInstance("SHA-256");
        generate();
    }

    public void generate() {
        //Создание байт ключа на основе key
        byte[] rawKey = getBytesOfKey(sixteenSymbolsKey);
        //Вычисляем дайджест
        rawKey = sha.digest(rawKey);
        rawKey = Arrays.copyOf(rawKey, 16);
        secretKey = new SecretKeySpec(rawKey, "AES");

        Path secretKeyPath = Paths.get("src\\KeyStorage\\secretAESKey.txt");
        writeKeyToFile(secretKeyPath, secretKey);
    }

    private void writeKeyToFile(Path secretKeyPath, Key secretKey) {
        try (BufferedWriter writer = Files.newBufferedWriter(secretKeyPath, StandardCharsets.UTF_8)){
            writer.write(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] getBytesOfKey(String sixteenSymbolsKey) {
        byte[] rawKey = sixteenSymbolsKey.getBytes(StandardCharsets.UTF_8);
        if (rawKey.length != 16)
            throw new IllegalArgumentException("Invalid key size. " + rawKey.length);
        return rawKey;
    }
}
