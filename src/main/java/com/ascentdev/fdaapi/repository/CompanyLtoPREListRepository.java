/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.repository;

import com.ascentdev.fdaapi.entity.CompanyLtoPREListEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author
 * ASCENT
 */

public interface CompanyLtoPREListRepository extends JpaRepository<CompanyLtoPREListEntity, Integer>{
  
//  @Query(value = "SELECT iSched.id, \n"
//        + "iSched.lto_application_id,\n"
//        + "iSched.permit_type, \n"
//        + "iSched.inspection_type, \n"
//        + "iSched.inspection_sched, \n"
//        + "cp.company_name, \n"
//        + "la.lto_number, \n"
//        + "la.prop_validity_period AS validity_date, \n"
//        + "rpt.name AS product_type, \n"
//        + "rpa.activity_name AS primary_activity, \n"
//        + "la.office_address,\n"
//        + "CONCAT(la.authorized_officer_firstname, ' ', la.authorized_officer_middlename, ' ', la.authorized_officer_lastname) AS contact_person,\n"
//        + "la.establishment_mobile AS contact_no, \n"
//        + "la.created_by_id\n"
//        + "FROM lto.lto_applications la\n"
//        + "LEFT JOIN commons.inspection_scheduling iSched ON iSched.lto_application_id = la.id\n"
//        + "LEFT JOIN commons.bridge_company_users bcu ON bcu.user_id = la.created_by_id\n"
//        + "LEFT JOIN cprs.company_profiles cp ON cp.id = bcu.company_profile_id\n"
//        + "INNER JOIN refs.ref_product_types rpt ON rpt.id = iSched.product_type_id\n"
//        + "INNER JOIN refs.ref_product_activities rpa ON rpa.id = iSched.primary_activity_id \n"
//        + "WHERE iSched.status = 'confirmed' AND la.created_by_id = :created_by_id", nativeQuery =  true)
  
  List<CompanyLtoPREListEntity> findByUserId(int userId);
  List<CompanyLtoPREListEntity> findByApplicationNo(String application_no);
  
  
  
  
}
