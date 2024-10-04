/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.serviceImplementation;

import com.ascentdev.fdaapi.entity.ApplicationItemsEntity;
import com.ascentdev.fdaapi.entity.CompanyCprPOSTListEntity;
import com.ascentdev.fdaapi.entity.CompanyCprPREListEntity;
import com.ascentdev.fdaapi.entity.CompanyLtoPOSTListEntity;
import com.ascentdev.fdaapi.entity.CompanyLtoPREListEntity;
import com.ascentdev.fdaapi.entity.DocumentListEntity;
import com.ascentdev.fdaapi.entity.InspectionLogsEntity;
import com.ascentdev.fdaapi.entity.InspectionSchedulesEntity;
import com.ascentdev.fdaapi.entity.RequiredFilesEntity;
import com.ascentdev.fdaapi.entity.UserIdByCompanyEntity;
import com.ascentdev.fdaapi.error.ErrorException;
import com.ascentdev.fdaapi.model.ApiResponseModel;
import com.ascentdev.fdaapi.model.LtoCprModel;
import com.ascentdev.fdaapi.repository.ApplicationItemsRepository;
import com.ascentdev.fdaapi.repository.CompanyCprPOSTListRepository;
import com.ascentdev.fdaapi.repository.CompanyCprPREListRepository;
import com.ascentdev.fdaapi.repository.CompanyLtoPOSTListRepository;
import com.ascentdev.fdaapi.repository.InspectionLogsRepository;
import com.ascentdev.fdaapi.repository.InspectionSchedulesRepository;
import com.ascentdev.fdaapi.repository.UserIdByCompanyRepository;
import com.ascentdev.fdaapi.service.ForInspectionService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ascentdev.fdaapi.repository.CompanyLtoPREListRepository;
import com.ascentdev.fdaapi.repository.DocumentListRepository;
import com.ascentdev.fdaapi.repository.RequiredFilesRepository;
import java.sql.Date;
import java.util.Arrays;

/**
 *
 * @author ASCENT
 */
@Service
public class ForInspectionServiceImplementation implements ForInspectionService {

  @Autowired
  CompanyLtoPREListRepository preLtoRepo;

  @Autowired
  CompanyLtoPOSTListRepository postLtoRepo;

  @Autowired
  CompanyCprPOSTListRepository postCprRepo;

  @Autowired
  CompanyCprPREListRepository preCprRepo;

  @Autowired
  InspectionLogsRepository inspectionRepo;

  @Autowired
  InspectionSchedulesRepository iSchedRepo;

  @Autowired
  UserIdByCompanyRepository uRepo;

  @Autowired
  RequiredFilesRepository fileRepo;

  @Autowired
  ApplicationItemsRepository appRepo;

  @Autowired
  DocumentListRepository docsRepo;

  String fileUploadPath = "C:\\APPS\\FDA\\FDA_IMAGES\\IMAGES";
  String fileUploadPath1 = "C:\\APPS\\FDA\\FDA_IMAGES\\SIGNATURES";

  @Override
  public ApiResponseModel getCompanyList(String company_name, String permit_type, String inspection_type) {

    ErrorException ex1 = null;
    ApiResponseModel model = new ApiResponseModel();

    List<CompanyCprPOSTListEntity> postCpr = new ArrayList<>();
    List<CompanyCprPREListEntity> preCpr = new ArrayList<>();
    List<CompanyLtoPOSTListEntity> postLto = new ArrayList<>();
    List<CompanyLtoPREListEntity> preLto = new ArrayList<>();
    UserIdByCompanyEntity userEntity = new UserIdByCompanyEntity();

    try {
      userEntity = uRepo.findByCompanyName(company_name);
      if (userEntity != null) {
        if (permit_type.contains("LTO") && inspection_type.contains("PRE-INSPECTION")) {
          preLto = preLtoRepo.findByUserId(userEntity.getUserId());
          if (preLto.size() > 0) {
            model.setData(preLto);
            model.setMessage("Data found");
            model.setStatus(true);
            model.setStatus_code(200);
          } else {
            model.setMessage("This Company has no schedule for Inspection");
            model.setStatus(false);
            model.setStatus_code(404);
          }
        } else if (permit_type.contains("LTO") && inspection_type.contains("POST-INSPECTION")) {
          postLto = postLtoRepo.findByUserId(userEntity.getUserId());
          if (postLto.size() > 0) {
            model.setData(postLto);
            model.setMessage("Data found");
            model.setStatus(true);
            model.setStatus_code(200);
          } else {
            model.setMessage("This Company has no schedule for Inspection");
            model.setStatus(false);
            model.setStatus_code(404);
          }
        } else if (permit_type.contains("CPR") && inspection_type.contains("PRE-INSPECTION")) {
          preCpr = preCprRepo.findByUserId(userEntity.getUserId());
          if (preCpr.size() > 0) {
            model.setData(preCpr);
            model.setMessage("Data found");
            model.setStatus(true);
            model.setStatus_code(200);
          } else {
            model.setMessage("This Company has no schedule for Inspection");
            model.setStatus(false);
            model.setStatus_code(404);
          }
        } else if (permit_type.contains("CPR") && inspection_type.contains("POST-INSPECTION")) {
          postCpr = postCprRepo.findByUserId(userEntity.getUserId());
          if (postCpr.size() > 0) {
            model.setData(postCpr);
            model.setMessage("Data found");
            model.setStatus(true);
            model.setStatus_code(200);
          } else {
            model.setMessage("This Company has no schedule for Inspection");
            model.setStatus(false);
            model.setStatus_code(404);
          }
        }
      } else {
        model.setMessage("No Company found");
        model.setStatus(false);
        model.setStatus_code(404);
      }

    } catch (ErrorException ex) {
      if (ex1 == null) {
        ex1 = new ErrorException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "Bad Request", System.currentTimeMillis());
      }
      throw ex1;
    }
    return model;
  }

  @Override
  public ApiResponseModel reschedInspection(int schedule_id, String date_from, String date_to, String remarks) {

    ErrorException ex1 = null;
    ApiResponseModel resp = new ApiResponseModel();
    LocalDateTime date = LocalDateTime.now();
    InspectionSchedulesEntity iSched = new InspectionSchedulesEntity();

    try {

      iSched = iSchedRepo.findById(schedule_id);

      if (iSched.getId() > 0) {
        iSched.setReschedRemarks(remarks);
        iSched.setReschedInspectionDateFrom(Timestamp.valueOf(date_from));
        iSched.setReschedInspectionDateTo(Timestamp.valueOf(date_to));
        iSched.setStatus("REJECTED");
        iSched.setUpdatedAt(Timestamp.valueOf(date));
        iSchedRepo.save(iSched);

        resp.setMessage("Reschedule of Inspection Completed!");
        resp.setStatus(true);
        resp.setStatus_code(200);
      } else {
        resp.setMessage("No record found...");
        resp.setStatus(true);
        resp.setStatus_code(400);
      }

    } catch (ErrorException e) {
      if (ex1 == null) {
        resp.setMessage("Reschedule Process was Unsuccessful");
        resp.setStatus(false);
        resp.setStatus_code(400);
      }
      throw ex1;
    }
    return resp;
  }

  @Override
  public ApiResponseModel saveInspectedLtoCpr(int schedule_id, String lto_no, String cpr_no, String permit_type, String product_type, String primary_activity,
          String company_name, String office_address, String contact_person, String contact_no, String inspection_type, int created_by_id,
          String remarks, String exit_remarks, String document_type, MultipartFile[] file, MultipartFile[] signFile) {

    ErrorException ex1 = null;
    ApiResponseModel resp = new ApiResponseModel();
    LocalDateTime date = LocalDateTime.now();
    InspectionLogsEntity inspection = new InspectionLogsEntity();
    InspectionSchedulesEntity iSched = new InspectionSchedulesEntity();
    RequiredFilesEntity reqEntity = new RequiredFilesEntity();

    try {

      iSched = iSchedRepo.findById(schedule_id);
      iSched.setRemarks(exit_remarks);
      iSched.setStatus("INSPECTED");
      iSchedRepo.save(iSched);

      String[] signature_type = {"CLIENT", "INSPECTOR"};
      String[] remarks1 = null;
      String[] documents = null;
      int document_id = 0;
      if (remarks != null && !remarks.isEmpty()) {
        remarks1 = remarks.split(",");
      }

      if (document_type != null && !document_type.isEmpty()) {
        documents = document_type.split(",");
      }
      int count = 0;
      int count1 = 0;

      for (MultipartFile f : file) {
        DocumentListEntity docEntity = docsRepo.findByName(documents[count]);
        if (docEntity != null) {
          document_id = docEntity.getId();
          reqEntity.setFilePath(fileUploadPath);
          reqEntity.setFileName(f.getOriginalFilename());
          reqEntity.setFileType("FILE");

          if (remarks1.length > 1) {
            reqEntity.setRemarks(remarks1[count]);
          } else {
            reqEntity.setRemarks(remarks1[0]);
          }

          reqEntity.setDocumentId(document_id);
          reqEntity.setInspectionSchedId(schedule_id);
          reqEntity.setCreatedAt(Timestamp.valueOf(date));

          fileRepo.save(reqEntity);

          reqEntity = new RequiredFilesEntity();
          saveImage(f);
        } else {
          // Handle the case where the document is not found
          System.out.println("Document with name " + documents[count] + " not found.");
        }
        count++;
      }

      for (MultipartFile s : signFile) {
        DocumentListEntity docEntity;
        if (documents.length > 1) {
          docEntity = docsRepo.findByName(documents[count1]);
        } else {
          docEntity = docsRepo.findByName(documents[0]);
        }

        if (docEntity != null) {
          document_id = docEntity.getId();

          reqEntity.setFilePath(fileUploadPath1);
          reqEntity.setFileName(s.getOriginalFilename());
          reqEntity.setFileType("SIGNATURE");
          reqEntity.setSignatoryType(signature_type[count1]);
          reqEntity.setDocumentId(document_id);
          reqEntity.setInspectionSchedId(schedule_id);
          reqEntity.setCreatedAt(Timestamp.valueOf(date));

          fileRepo.save(reqEntity);

          reqEntity = new RequiredFilesEntity();
          saveSignature(s);
        } else {
          // Handle the case where the document is not found
          System.out.println("Document with name " + documents[count1] + " not found.");
        }

        count1++;
      }

      resp.setMessage("Inspection Completed!");
      resp.setStatus(true);
      resp.setStatus_code(200);

    } catch (ErrorException e) {
      if (ex1 == null) {
        resp.setMessage("Inspection Not Completed!");
        resp.setStatus(false);
        resp.setStatus_code(400);
      }
      throw ex1;
    }
    return resp;
  }

  private void saveImage(MultipartFile file) {

    try {
      byte[] data = file.getBytes();
      Path path = Paths.get(fileUploadPath + "/" + file.getOriginalFilename());
      Files.write(path, data);
    } catch (IOException ex) {
      Logger.getLogger(ForInspectionServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void saveSignature(MultipartFile signFile) {

    try {
      byte[] data = signFile.getBytes();
      Path path = Paths.get(fileUploadPath1 + "/" + signFile.getOriginalFilename());
      Files.write(path, data);
    } catch (IOException ex) {
      Logger.getLogger(ForInspectionServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public ApiResponseModel getInspectionSched(String inspection_sched, int inspector_id) {

    ErrorException ex1 = null;
    ApiResponseModel model = new ApiResponseModel();

    List<InspectionSchedulesEntity> sched = new ArrayList<>();
    List<InspectionSchedulesEntity> fullSched = new ArrayList<>();

//    ApplicationItemsEntity appEntity = new ApplicationItemsEntity();
    List<CompanyCprPOSTListEntity> postCpr = new ArrayList<>();
    List<CompanyCprPREListEntity> preCpr = new ArrayList<>();
    List<CompanyLtoPOSTListEntity> postLto = new ArrayList<>();
    List<CompanyLtoPREListEntity> preLto = new ArrayList<>();

    try {
      sched = iSchedRepo.findByInspectionSchedAndStatusInAndInspectorId(Date.valueOf(inspection_sched), Arrays.asList("FOR_CONFIRMATION", "CONFIRMED"), inspector_id);
      fullSched = iSchedRepo.findByStatusInAndInspectorId(Arrays.asList("FOR_CONFIRMATION", "CONFIRMED"), inspector_id);

//      if (sched != null && sched.size() > 0) {
//        int i = 0;
//        model.setSchedData(sched);
//        model.setFullSchedData(fullSched);
        
//        for (i = 0; i < sched.size(); i++) {
//
//            if (sched.get(i).getDocumentType().equals("LTO")) {
//              preLto = preLtoRepo.findByApplicationNo(sched.get(i).getApplicationNumber());
//              if (preLto.size() > 0) {
//                model.setData(preLto);
//                model.setMessage("Data found");
//                model.setStatus(true);
//                model.setStatus_code(200);
//              } else {
//                model.setMessage("This Company has no schedule for Inspection");
//                model.setStatus(false);
//                model.setStatus_code(404);
//              }
//            } else if (sched.get(i).getDocumentType().equals("LTOR")) {
//              postLto = postLtoRepo.findByApplicationNo(sched.get(i).getApplicationNumber());
//              if (postLto.size() > 0) {
//                model.setData(postLto);
//                model.setMessage("Data found");
//                model.setStatus(true);
//                model.setStatus_code(200);
//              } else {
//                model.setMessage("This Company has no schedule for Inspection");
//                model.setStatus(false);
//                model.setStatus_code(404);
//              }
//            } else if (sched.get(i).getDocumentType().equals("CPR")) {
//              preCpr = preCprRepo.findByApplicationNo(sched.get(i).getApplicationNumber());
//              if (preCpr.size() > 0) {
//                model.setData(preCpr);
//                model.setMessage("Data found");
//                model.setStatus(true);
//                model.setStatus_code(200);
//              } else {
//                model.setMessage("This Company has no schedule for Inspection");
//                model.setStatus(false);
//                model.setStatus_code(404);
//              }
//            } else if (sched.get(i).getDocumentType().equals("CPRR")) {
//              postCpr = postCprRepo.findByApplicationNo(sched.get(i).getApplicationNumber());
//              if (postCpr.size() > 0) {
//                model.setData(postCpr);
//                model.setMessage("Data found");
//                model.setStatus(true);
//                model.setStatus_code(200);
//              } else {
//                model.setMessage("This Company has no schedule for Inspection");
//                model.setStatus(false);
//                model.setStatus_code(404);
//              }
//            }
//         
//        }



//      } else {
//        model.setData(null);
//        model.setMessage("No Data Found");
//        model.setStatus(false);
//        model.setStatus_code(404);
//      }



if (sched != null && sched.size() > 0) {
    for (int i = 0; i < sched.size(); i++) {
        String appNo = sched.get(i).getApplicationNumber();

        // Check for LTO
        preLto = preLtoRepo.findByApplicationNo(appNo);
        if (preLto != null && preLto.size() > 0) {
            model.setData(preLto);
            model.setMessage("LTO data found");
            model.setStatus(true);
            model.setStatus_code(200);
            continue;  // Move to the next iteration if data is found
        }

        // Check for LTOR
        postLto = postLtoRepo.findByApplicationNo(appNo);
        if (postLto != null && postLto.size() > 0) {
            model.setData(postLto);
            model.setMessage("LTOR data found");
            model.setStatus(true);
            model.setStatus_code(200);
            continue;
        }

        // Check for CPR
        preCpr = preCprRepo.findByApplicationNo(appNo);
        if (preCpr != null && preCpr.size() > 0) {
            model.setData(preCpr);
            model.setMessage("CPR data found");
            model.setStatus(true);
            model.setStatus_code(200);
            continue;
        }

        // Check for CPRR
        postCpr = postCprRepo.findByApplicationNo(appNo);
        if (postCpr != null && postCpr.size() > 0) {
            model.setData(postCpr);
            model.setMessage("CPRR data found");
            model.setStatus(true);
            model.setStatus_code(200);
            continue;
        }

        // If no data found in any case
        model.setMessage("This Company has no schedule for Inspection");
        model.setStatus(false);
        model.setStatus_code(404);
    }
} else {
    model.setData(null);
    model.setMessage("No Data Found");
    model.setStatus(false);
    model.setStatus_code(404);
}











    } catch (ErrorException ex) {
      if (ex1 == null) {
        ex1 = new ErrorException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "Bad Request", System.currentTimeMillis());
      }
      throw ex1;
    }
    return model;

  }

  @Override
  public ApiResponseModel getDocumentList() {
    ErrorException ex1 = null;
    ApiResponseModel resp = new ApiResponseModel();
    List<DocumentListEntity> documentList = new ArrayList<>();

    try {
      documentList = docsRepo.findAll();

      if (documentList.size() > 0) {
        resp.setDocumentList(documentList);
        resp.setMessage("Data found");
        resp.setStatus(true);
        resp.setStatus_code(200);

      } else {
        resp.setMessage("No Data found");
        resp.setStatus(false);
        resp.setStatus_code(404);
      }

    } catch (ErrorException ex) {
      if (ex1 == null) {
        ex1 = new ErrorException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "Bad Request", System.currentTimeMillis());
      }
      throw ex1;
    }

    return resp;
  }

  @Override
  public ApiResponseModel confirmInspection(int schedule_id) {

    ErrorException ex1 = null;
    ApiResponseModel resp = new ApiResponseModel();
    InspectionSchedulesEntity iSched = new InspectionSchedulesEntity();
    try {
      iSched = iSchedRepo.findById(schedule_id);
      if (iSched.getId() > 0) {
        iSched.setStatus("CONFIRMED");
        iSchedRepo.save(iSched);

        resp.setMessage("Schedule has been Confirmed!");
        resp.setStatus(true);
        resp.setStatus_code(200);
      } else {
        resp.setMessage("No schedule found!");
        resp.setStatus(false);
        resp.setStatus_code(401);
      }

    } catch (ErrorException e) {
      if (ex1 == null) {
        resp.setMessage("Failed to Confirm!");
        resp.setStatus(false);
        resp.setStatus_code(400);
      }
      throw ex1;
    }
    return resp;

  }

}
