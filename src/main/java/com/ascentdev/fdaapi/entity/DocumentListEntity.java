/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ascentdev.fdaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
@Entity
@Data
@Subselect("SELECT\n"
        + "	ic.* \n"
        + "FROM\n"
        + "	inspections.inspections_checklist ic\n"
        + "	INNER JOIN inspections.pvt_inspection_checklist_details pvt ON pvt.inspection_checklist_id = ic.ID \n"
        + "	INNER JOIN inspections.inspections_transaction it ON it.ID = pvt.inspection_transaction_id \n"
        + "WHERE\n"
        + "	it.product_type_id = 3 \n"
        + "	AND it.application_type_id = 1 \n"
        + "	AND it.services = 'license'\n"
        + "	AND ic.status = 'ACTIVE'")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DocumentListEntity {

  @Id
  int id;

  @Column(name = "document_description")
  String description;

  @Column(name = "document_name")
  String name;
  
  String status;

}
