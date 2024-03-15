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
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author ASCENT
 */
@Data
@Entity
@Subselect("SELECT id,\n"
        + "document_type,\n"
        + "document_id,\n"
        + "inspection_type,\n"
        + "application_number,\n"
        + "product_type_id,\n"
        + "primary_activity_id,\n"
        + "created_by_id,\n"
        + "application_type_id\n"
        + "FROM inspections.application_items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ApplicationItemsEntity {

  @Id
  @Column(name = "id")
  long id;

  @Column(name = "document_type")
  String documentType;

  @Column(name = "document_id")
  long documentId;

  @Column(name = "inspection_type")
  String inspectionType;

  @Column(name = "application_number")
  String applicationNumber;

  @Column(name = "product_type_id")
  int productTypeId;

  @Column(name = "primary_activity_id")
  int primaryActivityId;

  @Column(name = "created_by_id")
  int createdById;

  @Column(name = "application_type_id")
  int applicationTypeId;
}
