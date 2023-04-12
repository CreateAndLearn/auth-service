package com.work.entity;


import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "public")
@Builder
public class User {

  @Id
  private UUID id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "email_id")
  private String email;
  @Column(name = "phone_no")
  private String phoneNo;
  @Column(name = "password_hash")
  private String password;
  @Column(name = "salt")
  private byte[] salt;

}
