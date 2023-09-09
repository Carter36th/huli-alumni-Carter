package com.wanderer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String nickname;
  private String password;
  private Date birthDate;

  public Long getId() {
    return id;
  }

  public String getNickName() {
    return nickname;
  }

  public String getPassword() {
    return password;
  }
}
