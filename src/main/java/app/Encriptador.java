package app;

import io.github.cdimascio.dotenv.Dotenv;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Encriptador {
    
    private static final String KEY = Dotenv.configure().load().get("ENCRYPT_PASSWORD");
    
    public static String encrypt(String password) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(KEY);
        return encryptor.encrypt(password);
    }
    
    public static String decrypt(String encryptedPassword) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(KEY);
        return encryptor.decrypt(encryptedPassword);
    }
}
