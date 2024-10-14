/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ascentdev.fdaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
@Entity
@Data
@Table(name = "notifications_table", schema = "commons")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NotificationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "notification_object")
  int notificationObject;

  @Column(name = "process_flow_id")
  int processFlowId;

  @Column(name = "client_id")
  int clientId;

  @Column(name = "is_seen")
  boolean isSeen;

  @Column(name = "created_dt")
  Timestamp createdDt;

  @Column(name = "application_id")
  int applicationId;

  String module;
  
  @Column(name = "application_number")
  String applicationNumber;

  @Column(name = "role_id")
  int roleId;

  @Column(name = "application_type")
  String applicationType;

  @Column(name = "is_process")
  boolean isProcess;
}
