/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ascentdev.fdaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
@Entity
@Data
@Table(name="ref_documents", schema="refs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DocumentListEntity {
  
  @Id
  int id;
  
  String name;
  
}
