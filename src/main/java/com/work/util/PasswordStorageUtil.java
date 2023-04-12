package com.work.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

@Component
public class PasswordStorageUtil {

  private static final int ITERATIONS = 65536;
  private static final int KEY_LENGTH = 256;
  private static final int SALT_LENGTH = 16;

  public  byte[] generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[SALT_LENGTH];
    random.nextBytes(salt);
    return salt;
  }

  public  String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

    PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    byte[] hash = skf.generateSecret(spec).getEncoded();

    SecretKeySpec keySpec = new SecretKeySpec(hash, "HmacSHA256");
    SecretKey key = keySpec;
    byte[] saltedHash = key.getEncoded();

    byte[] saltedHashWithSalt = new byte[saltedHash.length + salt.length];
    System.arraycopy(saltedHash, 0, saltedHashWithSalt, 0, saltedHash.length);
    System.arraycopy(salt, 0, saltedHashWithSalt, saltedHash.length, salt.length);

    return Base64.getEncoder().encodeToString(saltedHashWithSalt);
  }

}
