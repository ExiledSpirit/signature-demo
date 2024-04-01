package com.assinatura.assinatura.models;

import com.assinatura.assinatura.helper.HashingUtilities;

public class Signature {
  private Long id;
  private String signatureHash;
  private Author author;
  private Document document;

  public Signature(Long id, Author author, Document document) {
    this.id = id;
    this.author = author;
    this.document = document;
    this.signatureHash = this.generateSignature();
  }

  public Signature(Long id, String signatureHash, Author author, Document document) {
    this.id = id;
    this.signatureHash = signatureHash;
    this.author = author;
    this.document = document;
  }

  public Signature() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSignatureHash() {
    return signatureHash;
  }

  public void setSignatureHash(String signatureHash) {
    this.signatureHash = signatureHash;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Document getDocument() {
    return document;
  }

  public void setDocument(Document document) {
    this.document = document;
  }

  @Override
  public String toString() {
    return "Signature [id=" + id + ", signatureHash=" + signatureHash + ", author=" + author + ", document=" + document
        + "]";
  }

  public boolean validate() {
    if (!this.document.validate()) {
      System.out.println("Document hash is invalid!");
      return false;
    }

    if (!this.generateSignature().equals(this.signatureHash)) {
      System.out.println("Generated signature doesnt match!");
      return false;
    }

    if (!this.signatureToHash().equals(this.document.getHash())) {
      System.out.println("Private key cant decrypt!");
      return false;
    }

    return true;
  }

  public String signatureToHash() {
    try {
      return HashingUtilities.decryptMessage(this.author.getPublicKey(), signatureHash);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public String generateSignature() {
    try {
      return HashingUtilities.encryptMessage(this.author.getPrivateKey(), this.document.getHash());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
