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
@Subselect("SELECT iSched.id,\n"
        + "u.id AS user_id, \n"
        + "-- lah.id AS lto_application_id,\n"
        + "iSched.inspector_id,\n"
        + "iSched.application_number,\n"
        + "iSched.document_type,\n"
        + "iSched.inspection_type,\n"
        + "iSched.inspection_date,\n"
        + "iSched.status,\n"
        + "iSched.inspection_time_from,\n"
        + "iSched.inspection_time_to,\n"
        + "ubp.name as company_name,\n"
        + "lr.lto_number,\n"
        + "lr.validity_date,\n"
        + "rpt.name AS product_type,\n"
        + "rpa.name AS primary_activity,\n"
        + "CONCAT(lra.street_name, ',', rm.citymun_desc, ',', pr.prov_desc) AS office_address,\n"
        + "CONCAT(up.first_name, ' ' ,up.last_name) as contact_person,\n"
        + "lr.establishment_mobile AS contact_no\n"
        + "FROM inspections.inspection_schedules iSched\n"
        + "INNER JOIN lto.lto_records lr ON lr.inspection_schedule_id = iSched.id\n"
        + "INNER JOIN commons.users u ON u.id = lr.created_by_id\n"
        + "INNER JOIN commons.user_profiles up ON up.id = u.user_profile_id\n"
        + "INNER JOIN commons.user_business_profiles ubp ON ubp.id = u.business_profile_id\n"
        + "INNER JOIN refs.ref_product_types rpt ON rpt.id = iSched.product_type_id\n"
        + "INNER JOIN refs.ref_activities rpa ON rpa.id = iSched.primary_activity_id \n"
        + "INNER JOIN lto.lto_record_addresses lra ON lra.id = lr.office_address_id\n"
        + "INNER JOIN refs.ref_municipalities rm ON rm.muni_id = lra.city_municipal_id\n"
        + "INNER JOIN refs.ref_provinces pr ON pr.province_id = lra.province_id\n"
        + "WHERE iSched.status IN ('FOR_CONFIRMATION', 'CONFIRMED')")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CompanyLtoPOSTListEntity {

  @Id
  int id;

  @Column(name = "user_id")
  int userId;

  @Column(name = "inspector_id")
  int inspectorId;

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

  @Column(name = "lto_number")
  String ltoNumber;

  @Column(name = "validity_date")
  String dateTime;

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

  String status;

  @Column(name = "inspection_time_from")
  String inspectionTimeFrom;

  @Column(name = "inspection_time_to")
  String inspectionTimeTo;
//    @Column(name = "company_profile_id")
//    int companyProfileId;

//  @Column(name = "lto_application_id")
//  int ltoApplicationId;
}
