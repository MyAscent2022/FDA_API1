/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.entity;

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
@Subselect("SELECT \n"
        + "iSched.id,\n"
        + "u.id AS user_id,\n"
        + "ca.application_number,\n"
        + "ai.document_type,\n"
        + "ai.inspection_type,\n"
        + "iSched.inspection_date,\n"
        + "ubp.name as company_name,\n"
        + "rpt.name AS product_type,\n"
        + "rpa.activity_name AS primary_activity,\n"
        + "CONCAT(laa.street_name, ',', rm.citymun_desc, ',', pr.prov_desc) AS office_address,\n"
        + "CONCAT(up.first_name, ' ' ,up.last_name) as contact_person,\n"
        + "ubp.mobile_no as contact_no\n"
        + "FROM inspections.application_items ai\n"
        + "INNER JOIN inspections.inspection_schedules iSched ON iSched.application_item_id = ai.id\n"
        + "AND iSched.application_item_type = ai.document_type\n"
        + "INNER JOIN cpr.cpr_applications ca ON ca.application_number = ai.application_number\n"
        + "LEFT JOIN cpr.cpr_records cr ON cr.id = ca.cpr_record_id\n"
        + "INNER JOIN commons.users u ON u.id = ca.created_by_id\n"
        + "INNER JOIN commons.user_profiles up ON up.id = u.user_profile_id\n"
        + "INNER JOIN commons.user_business_profiles ubp ON ubp.id = u.business_profile_id\n"
        + "INNER JOIN refs.ref_product_types rpt ON rpt.id = ai.product_type_id\n"
        + "INNER JOIN refs.ref_product_activities rpa ON rpa.id = ai.primary_activity_id \n"
        + "INNER JOIN lto.lto_application_addresses laa ON laa.created_by_id = u.id\n"
        + "INNER JOIN refs.ref_municipalities rm ON rm.muni_id = laa.city_municipal_id\n"
        + "INNER JOIN refs.ref_provinces pr ON pr.province_id = laa.province_id\n"
        + "WHERE iSched.status = 'confirmed'")
//@Subselect("SELECT iSched.id, \n"
//        + "cp.id AS company_profile_id, \n"
//        + "iSched.lto_application_id,\n"
//        + "ca.application_no, \n"
//        + "iSched.permit_type, \n"
//        + "iSched.inspection_type, \n"
//        + "iSched.inspection_sched, \n"
//        + "cp.company_name, \n"
//        + "lr.lto_number, \n"
//        + "cr.cpr_no, \n"
//        + "lr.validity_date, \n"
//        + "rpt.name AS product_type, \n"
//        + "rpa.activity_name AS primary_activity, \n"
//        + "lr.office_address,\n"
//        + "CONCAT(lr.authorized_officer_firstname, ' ', lr.authorized_officer_middlename, ' ', lr.authorized_officer_lastname) AS contact_person,\n"
//        + "lr.establishment_mobile AS contact_no,\n"
//        + "cr.created_by_id"
//        + "FROM cpr.cpr_records cr \n"
//        + "LEFT JOIN lto.lto_records lr ON lr.id =  cr.lto_record_id \n"
//        + "LEFT JOIN commons.inspection_scheduling iSched ON iSched.lto_application_id = lr.lto_application_id \n"
//        + "INNER JOIN cpr.cpr_applications ca ON ca.cpr_no = cr.cpr_no \n"
//        + "LEFT JOIN commons.bridge_company_users bcu ON bcu.user_id = cr.created_by_id \n"
//        + "LEFT JOIN cprs.company_profiles cp ON cp.id = bcu.company_profile_id \n"
//        + "INNER JOIN refs.ref_product_types rpt ON rpt.id = iSched.product_type_id \n"
//        + "INNER JOIN refs.ref_product_activities rpa ON rpa.id = iSched.primary_activity_id \n"
//        + "WHERE iSched.status = 'confirmed'")
public class CompanyCprPOSTListEntity {

  @Id
  int id;

  @Column(name = "user_id")
  int userId;

  @Column(name = "application_number")
  String applicationNo;

  @Column(name = "document_type")
  String documentType;

  @Column(name = "inspection_type")
  String inspectionType;

  @Column(name = "inspection_date")
  Date inspectionSched;

  @Column(name = "company_name")
  String companyName;

  @Column(name = "product_type")
  String productType;

  @Column(name = "primary_activity")
  String primaryActivity;

  @Column(name = "contact_person")
  String contactPerson;

  @Column(name = "office_address")
  String officeAddress;

  @Column(name = "contact_no")
  String contactNo;
}
