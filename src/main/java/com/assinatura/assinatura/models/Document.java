package com.assinatura.assinatura.models;

import com.assinatura.assinatura.helper.HashingUtilities;

public class Document {
  private Long id;
  private Author author;
  private String hash;
  private String content;

  public Document(Long id, Author author, String content) {
    this.id = id;
    this.author = author;
    this.content = content;

    this.hash = this.generateHash();
  }

  public Document(Long id, Author author, String hash, String content) {
    this.id = id;
    this.author = author;
    this.hash = hash;
    this.content = content;
  }

  public Document() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean validate() {
    System.out.println("Hash " + this.hash);
    System.out.println("new Hash " + this.generateHash());
    return this.hash.equals(this.generateHash());
  }

  private String generateHash() {
    try {
      return HashingUtilities.hash(content);
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }

  @Override
  public String toString() {
    return "Document [id=" + id + ", author=" + author + ", hash=" + hash + ", content=" + content + "]";
  }

}
