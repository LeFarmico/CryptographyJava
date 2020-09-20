package KeyStorage;

import java.io.File;

public class KeyStorage {

    private File pubKey = new File("src/KeyStorage/publicRSAKey.txt");
    private File prvKey = new File("src/KeyStorage/privateRSAKey.txt");
    private File secKey = new File("src/KeyStorage/secretAESKey.txt");
    private File imgKey = new File("src/KeyStorage/Image_key.png");

    public File getPubKey() {
        return pubKey;
    }

    public File getPrvKey() {
        return prvKey;
    }

    public File getSecKey() {
        return secKey;
    }

    public File getImgKey() {
        return imgKey;
    }
}
