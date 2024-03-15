/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.hibernate.annotations.Subselect;

/**
 *
 * @author
 * ASCENT
 */
@Data
@Entity
@Table(name = "inspection_logs_activity", schema = "inspections")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InspectionLogsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  @Column(name = "lto_no")
  String ltoNo;

  @Column(name = "cpr_no")
  String cprNo;

  @Column(name = "permit_type")
  String permitType;

  @Column(name = "primary_activity")
  String primaryActivity;

  @Column(name = "product_type")
  String productType;

  @Column(name = "companyName")
  String companyName;

  @Column(name = "address")
  String address;

  @Column(name = "contact_person")
  String contactPerson;

  @Column(name = "contact_no")
  String contactNo;

  @Column(name = "inspection_dt")
  Timestamp inspectionDt;

  @Column(name = "inspection_type")
  String inspectionType;

  @Column(name = "created_at")
  Timestamp createdAt;

  @Column(name = "created_by_id")
  int createdById;

  @Column(name = "company_id")
  int companyId;

  @Column(name = "filepath")
  String filePath;

  @Column(name = "filename")
  String fileName;

  @Column(name = "remarks")
  String remarks;

//  @Column(name = "exit_remarks")
//  String exitRemarks;

  @Column(name = "document_type")
  String documentType;

}
