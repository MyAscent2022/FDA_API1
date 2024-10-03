/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.controller;

import com.ascentdev.fdaapi.model.ApiResponseModel;
import com.ascentdev.fdaapi.serviceImplementation.ForInspectionServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASCENT
 */
@RestController
@RequestMapping("/fda_mobile/")
public class InspectionController {

  @Autowired
  ForInspectionServiceImplementation inspectionServiceImp;

  @GetMapping("search_company")
  public ApiResponseModel searchCompany(@RequestParam("company_name") String company_name, @RequestParam("permit_type") String permit_type, @RequestParam("inspection_type") String inspection_type) {
    return inspectionServiceImp.getCompanyList(company_name, permit_type, inspection_type);
  }

  @GetMapping("search_inspection_sched")
  public ApiResponseModel searcSched(@RequestParam("inspection_sched") String inspection_sched, @RequestParam("inspector_id") int inspector_id) {
    return inspectionServiceImp.getInspectionSched(inspection_sched, inspector_id);
  }

  @GetMapping("get_documents")
  public ApiResponseModel getDocumentList() {
    return inspectionServiceImp.getDocumentList();
  }

  @PostMapping("save_inspection")
  public ApiResponseModel saveInspection(@RequestParam("schedule_id") int schedule_id,
          @RequestParam("lto_number") String lto_no,
          @RequestParam("cpr_no") String cpr_no,
          @RequestParam("permit_type") String permit_type,
          @RequestParam("product_type") String product_type,
          @RequestParam("primary_activity") String primary_activity,
          @RequestParam("company_name") String company_name,
          @RequestParam("office_address") String office_address,
          @RequestParam("contact_person") String contact_person,
          @RequestParam("contact_no") String contact_no,
          @RequestParam("inspection_type") String inspection_type,
          @RequestParam("created_by_id") int created_by_id,
          @RequestParam("remarks") String remarks,
          @RequestParam("exit_remarks") String exit_remarks,
          @RequestParam("document_type") String document_type,
          @RequestParam("file[]") MultipartFile[] file,
          @RequestParam("signFile[]") MultipartFile[] signFile) {
//    MultipartFile[] file = null;
    return inspectionServiceImp.saveInspectedLtoCpr(schedule_id, lto_no, cpr_no, permit_type, product_type, primary_activity, company_name, office_address, contact_person, contact_no,
            inspection_type, created_by_id, remarks, exit_remarks, document_type, file, signFile);
  }

  @PostMapping("reschedule_inspection")
  public ApiResponseModel reschedInspection(@RequestParam("schedule_id") int schedule_id, @RequestParam("date_from") String date_from, @RequestParam("date_to") String date_to, @RequestParam("remarks") String remarks) {
    return inspectionServiceImp.reschedInspection(schedule_id, date_from, date_to, remarks);
  }

  @PostMapping("confirm_inspection")
  public ApiResponseModel confirmInspection(@RequestParam("schedule_id") int schedule_id) {
    return inspectionServiceImp.confirmInspection(schedule_id);
  }
}
