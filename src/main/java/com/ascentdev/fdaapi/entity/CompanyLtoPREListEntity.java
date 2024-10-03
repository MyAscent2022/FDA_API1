/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author ASCENT
 */
@Data
@Entity
//@Subselect("SELECT iSched.id,\n"
//        + "u.id AS user_id,\n"
//        + "la.application_number, \n"
//        + "la.id AS lto_application_id, \n"
//        + "ai.document_type,\n"
//        + "ai.inspection_type,\n"
//        + "iSched.inspection_date,\n"
//        + "ubp.name as company_name,\n"
//        + "rpt.name AS product_type,\n"
//        + "rpa.name AS primary_activity,\n"
//        + "CONCAT(laa.street_name, ',', rm.citymun_desc, ',', pr.prov_desc) AS office_address,\n"
//        + "CONCAT(up.first_name, ' ' ,up.last_name) as contact_person,\n"
//        + "la.establishment_mobile AS contact_no\n"
//        + "FROM inspections.application_items ai\n"
//        + "INNER JOIN inspections.inspection_schedules iSched ON iSched.application_item_id = ai.id\n"
//        + "AND iSched.document_id = ai.document_id\n"
//        + "INNER JOIN lto.lto_applications la ON la.application_number = ai.application_number\n"
//        + "INNER JOIN commons.users u ON u.id = la.created_by_id\n"
//        + "INNER JOIN commons.user_profiles up ON up.id = u.user_profile_id\n"
//        + "INNER JOIN commons.user_business_profiles ubp ON ubp.id = u.business_profile_id\n"
//        + "INNER JOIN refs.ref_product_types rpt ON rpt.id = ai.product_type_id\n"
//        + "INNER JOIN refs.ref_activities rpa ON rpa.id = ai.primary_activity_id \n"
//        + "INNER JOIN lto.lto_application_addresses laa ON laa.id = la.office_address_id\n"
//        + "INNER JOIN refs.ref_municipalities rm ON rm.muni_id = laa.city_municipal_id\n"
//        + "INNER JOIN refs.ref_provinces pr ON pr.province_id = laa.province_id\n"
//        + "WHERE iSched.status = 'CONFIRMED'")
@Subselect("SELECT iSched.id,\n"
        + "u.id AS user_id,\n"
        + "iSched.inspector_id,\n"
        + "la.application_number, \n"
        + "la.id AS lto_application_id, \n"
        + "iSched.document_type,\n"
        + "iSched.inspection_type,\n"
        + "iSched.inspection_date, \n"
        + "iSched.status,\n"
        + "iSched.inspection_time_from,\n"
        + "iSched.inspection_time_to,\n"
        + "ubp.name as company_name,\n"
        + "rpt.name AS product_type,\n"
        + "rpa.name AS primary_activity,\n"
        + "CONCAT(laa.street_name, ',', rm.citymun_desc, ',', pr.prov_desc) AS office_address,\n"
        + "CONCAT(up.first_name, ' ' ,up.last_name) as contact_person,\n"
        + "la.establishment_mobile AS contact_no\n"
        + "FROM inspections.inspection_schedules iSched\n"
        + "INNER JOIN lto.lto_applications la ON la.application_number = iSched.application_number\n"
        + "INNER JOIN commons.users u ON u.id = la.created_by_id\n"
        + "INNER JOIN commons.user_profiles up ON up.id = u.user_profile_id\n"
        + "INNER JOIN commons.user_business_profiles ubp ON ubp.id = u.business_profile_id\n"
        + "INNER JOIN refs.ref_product_types rpt ON rpt.id = iSched.product_type_id\n"
        + "INNER JOIN refs.ref_activities rpa ON rpa.id = iSched.primary_activity_id \n"
        + "INNER JOIN lto.lto_application_addresses laa ON laa.id = la.office_address_id\n"
        + "INNER JOIN refs.ref_municipalities rm ON rm.muni_id = laa.city_municipal_id\n"
        + "INNER JOIN refs.ref_provinces pr ON pr.province_id = laa.province_id\n"
        + "WHERE iSched.status IN ('FOR_CONFIRMATION', 'CONFIRMED')")
public class CompanyLtoPREListEntity {

  @Id
  int id;

  @Column(name = "user_id")
  int userId;

  @Column(name = "application_number")
  String applicationNo;

  @Column(name = "document_type")
  String documentType;

  @Column(name = "inspector_id")
  int inspectorId;

  @Column(name = "inspection_type")
  String inspectionType;

  @Column(name = "inspection_date")
  Date inspectionSched;

  @Column(name = "company_name")
  String companyName;

//    @Column(name = "lto_number")
//    String ltoNumber;
//    @Column(name = "validity_date")
//    String dateTime;
  @Column(name = "product_type")
  String productType;

  @Column(name = "primary_activity")
  String primaryActivity;

  @Column(name = "office_address")
  String officeAddress;

  @Column(name = "contact_person")
  String contactPerson;

  @Column(name = "contact_no")
  String contactNo;
//    @Column(name = "company_profile_id")
//    int companyProfileId;

  @Column(name = "lto_application_id")
  int ltoApplicationId;

  String status;

  @Column(name = "inspection_time_from")
  String inspectionTimeFrom;

  @Column(name = "inspection_time_to")
  String inspectionTimeTo;

}
