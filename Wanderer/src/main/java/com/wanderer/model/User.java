package com.wanderer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String nickname;
  private String password;
  private Date birthDate;

  public User(Long id, String firstName, String lastName, String nickname, String password,
      Date birthDate) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.password = password;
    this.birthDate = birthDate;
  }

  public User() {
  }

  public Long getId() {
    return id;
  }

  public String getNickname() {
    return nickname;
  }

  public String getPassword() {
    return password;
  }
}
