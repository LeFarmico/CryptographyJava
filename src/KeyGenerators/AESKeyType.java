package KeyGenerators;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AESKeyType {

    String sixteenSymbolsKey;
    MessageDigest sha;

    public AESKeyType(String sixteenSymbolsKey)
            throws NoSuchAlgorithmException {
        this.sixteenSymbolsKey = sixteenSymbolsKey;
        //MessageDigest будет проверять ключ на целостность
        this.sha = MessageDigest.getInstance("SHA-256");
        generate();
    }

    public void generate() {
        //Создание байт ключа на основе key
        byte[] rawKey = sixteenSymbolsKey.getBytes(StandardCharsets.UTF_8);
        if (rawKey.length != 16)
            throw new IllegalArgumentException("Invalid key size. " + rawKey.length);
        //Вычисляем дайджест
        rawKey = sha.digest(rawKey);
        rawKey = Arrays.copyOf(rawKey, 16);

        Path secretKeyPath = Paths.get("src\\KeyStorage\\secretAESKey.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(secretKeyPath, StandardCharsets.UTF_8)){
            writer.write(Base64.getEncoder().encodeToString(rawKey));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
