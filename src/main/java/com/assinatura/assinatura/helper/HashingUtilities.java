package com.assinatura.assinatura.helper;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class HashingUtilities {
  public static String hash(String message) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] digest = md.digest(message.getBytes());
    String checksum = Base64.getEncoder().encodeToString(digest);
    return checksum;
  }

  public static KeyPair generateKeyPair() throws Exception {
    return generateKeyPairPrivate();
  }

  private static KeyPair generateKeyPairPrivate() throws Exception {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);
    return keyPairGenerator.generateKeyPair();
  }

  public static String encryptMessage(String privateKeyString, String message) throws Exception {
    Key privateKey = stringToPrivateKey(privateKeyString);
    Cipher encryptCipher = Cipher.getInstance("RSA");
    encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);
    byte[] secretMessageBytes = message.getBytes(StandardCharsets.UTF_8);
    byte[] encodedMessage = encryptCipher.doFinal(secretMessageBytes);
    String encryptedMessage = Base64.getEncoder().encodeToString(encodedMessage);
    return encryptedMessage;
  }

  public static String decryptMessage(String publicKeyString, String encryptedMessage) throws Exception {
    byte[] encryptedMessageBytes = Base64.getDecoder().decode(encryptedMessage);
    Key publicKey = stringToPublicKey(publicKeyString);
    Cipher decryptCipher = Cipher.getInstance("RSA");
    decryptCipher.init(Cipher.DECRYPT_MODE, publicKey);
    byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
    String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
    return decryptedMessage;
  }

  private static PrivateKey stringToPrivateKey(String stringKey)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] keyByte = Base64.getDecoder().decode(stringKey);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey key = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyByte));
    return key;
  }

  private static PublicKey stringToPublicKey(String stringKey)
      throws InvalidKeySpecException, NoSuchAlgorithmException {
    byte[] keyByte = Base64.getDecoder().decode(stringKey);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey key = keyFactory.generatePublic(new X509EncodedKeySpec(keyByte));
    return key;
  }
}
