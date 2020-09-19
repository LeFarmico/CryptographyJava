package KeyGenerators;

import java.nio.charset.StandardCharsets;
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
        generateKey();
    }

    public String generateKey() {
        //Создание байт ключа на основе key
        byte[] rawKey = sixteenSymbolsKey.getBytes(StandardCharsets.UTF_8);
        if (rawKey.length != 16)
            throw new IllegalArgumentException("Invalid key size. " + rawKey.length);
        //Вычисляем дайджест
        rawKey = sha.digest(rawKey);
        rawKey = Arrays.copyOf(rawKey, 16);
        return Base64.getEncoder().encodeToString(rawKey);
    }
}
