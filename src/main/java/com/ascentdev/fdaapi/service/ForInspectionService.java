/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.service;

import com.ascentdev.fdaapi.model.ApiResponseModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author
 * ASCENT
 */

public interface ForInspectionService {
  
  ApiResponseModel getCompanyList(String company_name, String permit_type, String inspection_type);
//  ApiResponseModel saveInspectionData();
  
  ApiResponseModel saveInspectedLtoCpr(int schedule_id,String lto_no, String cpr_no, String permit_type, String product_type, String primary_activity, 
          String company_name, String office_address, String contact_person, String contact_no, String inspection_type, int created_by_id, 
          String remarks, String exit_remarks, String document_type, MultipartFile[] file, MultipartFile[] signFile);
  
  ApiResponseModel getInspectionSched(String inspection_sched);
}
