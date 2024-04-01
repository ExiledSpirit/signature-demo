package com.assinatura.assinatura.models;

import java.security.KeyPair;
import java.util.Base64;

import com.assinatura.assinatura.helper.HashingUtilities;

public class Author {
  private Long id;
  private String privateKey;
  private String publicKey;

  public Author(Long id) {
    this.id = id;

    try {
      KeyPair keyPair = HashingUtilities.generateKeyPair(); // Throws

      String privateKeyString = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
      String publicKeyString = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
      this.privateKey = privateKeyString;
      this.publicKey = publicKeyString;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Author(Long id, String privateKey, String publicKey) {
    this.id = id;
    this.privateKey = privateKey;
    this.publicKey = publicKey;
  }

  public Author() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  @Override
  public String toString() {
    return "Author [id=" + id + ", privateKey=" + privateKey + ", publicKey=" + publicKey + "]";
  }

}
