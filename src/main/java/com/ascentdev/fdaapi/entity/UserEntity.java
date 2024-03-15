/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author
 * Carlo
 * Dela
 * Pena
 */
@Data
@Entity
@Table(name = "users", schema = "commons")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {

  @Id
  int id;
  
  @Column(name="username")
  String username;
  
  @Column(name="passkey")
  String passkey;
  
  @Column(name="email")
  String email;
  
//  @Column(name="profile_id")
//  int profileId;
  
  @Column(name="user_profile_id")
  int userProfileId;
  
  @Column(name="role_id")
  int roleId;
  //int profile_id;
}
