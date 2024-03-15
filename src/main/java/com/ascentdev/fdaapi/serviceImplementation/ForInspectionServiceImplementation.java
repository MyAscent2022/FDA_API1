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
import com.ascentdev.fdaapi.repository.RequiredFilesRepository;
import java.sql.Date;

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

    String fileUploadPath = "C:\\FDA_IMAGES\\IMAGES";
    String fileUploadPath1 = "C:\\FDA_IMAGES\\SIGNATURES";

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
            iSched.setStatus("inspected");
            iSchedRepo.save(iSched);

            String[] signature_type = {"CLIENT", "INSPECTOR"};

            String[] remarks1 = remarks.split(",");
            String[] docs_type = document_type.split(",");
            int count = 0;
            int count1 = 0;

            for (MultipartFile f : file) {

                reqEntity.setFilePath(fileUploadPath);
                reqEntity.setFileName(f.getOriginalFilename());
                reqEntity.setFileType("FILE");
                reqEntity.setRemarks(remarks1[count]);
                reqEntity.setDocumentId(iSched.getDocumentId());
                reqEntity.setInspectionSchedId(schedule_id);
                reqEntity.setCreatedAt(Timestamp.valueOf(date));
                fileRepo.save(reqEntity);
                saveImage(f);
                count++;

            }

            for (MultipartFile s : signFile) {

                reqEntity.setFilePath(fileUploadPath);
                reqEntity.setFileName(s.getOriginalFilename());
                reqEntity.setFileType("SIGNATURE");
                reqEntity.setSignatoryType(signature_type[count1]);
                reqEntity.setDocumentId(iSched.getDocumentId());
                reqEntity.setInspectionSchedId(schedule_id);
                reqEntity.setCreatedAt(Timestamp.valueOf(date));
                fileRepo.save(reqEntity);
                saveSignature(s);
                count++;
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
    public ApiResponseModel getInspectionSched(String inspection_sched) {

        ErrorException ex1 = null;
        ApiResponseModel model = new ApiResponseModel();

        List<InspectionSchedulesEntity> sched = new ArrayList<>();
        List<InspectionSchedulesEntity> fullSched = new ArrayList<>();

        ApplicationItemsEntity appEntity = new ApplicationItemsEntity();

        List<CompanyCprPOSTListEntity> postCpr = new ArrayList<>();
        List<CompanyCprPREListEntity> preCpr = new ArrayList<>();
        List<CompanyLtoPOSTListEntity> postLto = new ArrayList<>();
        List<CompanyLtoPREListEntity> preLto = new ArrayList<>();

        try {
            sched = iSchedRepo.findByInspectionSchedAndStatus(Date.valueOf(inspection_sched), "confirmed");
            fullSched = iSchedRepo.findByStatus("confirmed");

            if (sched.size() > 0) {
                int i = 0;
                model.setSchedData(sched);
                model.setFullSchedData(fullSched);
                for (i = 0; i < sched.size(); i++) {

                    appEntity = appRepo.findByDocumentTypeAndId(sched.get(i).getApplicationItemType(), sched.get(i).getApplicationItemId());

                    if (appEntity != null) {
                        if (appEntity.getDocumentType().contains("LTO") && appEntity.getInspectionType().contains("PRE-INSPECTION")) {
                            preLto = preLtoRepo.findByApplicationNo(appEntity.getApplicationNumber());
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
                        } else if (appEntity.getDocumentType().contains("LTO") && appEntity.getInspectionType().contains("POST-INSPECTION")) {
                            postLto = postLtoRepo.findByApplicationNo(appEntity.getApplicationNumber());
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
                        } else if (appEntity.getDocumentType().contains("CPR") && appEntity.getInspectionType().contains("PRE-INSPECTION")) {
                            preCpr = preCprRepo.findByApplicationNo(appEntity.getApplicationNumber());
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
                        } else if (appEntity.getDocumentType().contains("CPR") && appEntity.getInspectionType().contains("POST-INSPECTION")) {
                            postCpr = postCprRepo.findByApplicationNo(appEntity.getApplicationNumber());
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
                    }
                }

//                    if (sched.get(i).getPermitType().contains("LTO") && sched.get(i).getInspectionType().contains("PRE-INSPECTION")) {
//                        preLto = preLtoRepo.findByApplicationNo(sched.get(i).getApplicationId());
//                        if (preLto.size() > 0) {
//                            model.setData(preLto);
//                            model.setMessage("Data found");
//                            model.setStatus(true);
//                            model.setStatus_code(200);
//                        } else {
//                            model.setMessage("This Company has no schedule for Inspection");
//                            model.setStatus(false);
//                            model.setStatus_code(404);
//                        }
//                    } else if (sched.get(i).getPermitType().contains("LTO") && sched.get(i).getInspectionType().contains("POST-INSPECTION")) {
//                        postLto = postLtoRepo.findByApplicationNo(sched.get(i).getApplicationId());
//                        if (postLto.size() > 0) {
//                            model.setData(postLto);
//                            model.setMessage("Data found");
//                            model.setStatus(true);
//                            model.setStatus_code(200);
//                        } else {
//                            model.setMessage("This Company has no schedule for Inspection");
//                            model.setStatus(false);
//                            model.setStatus_code(404);
//                        }
//                    } else if (sched.get(i).getPermitType().contains("CPR") && sched.get(i).getInspectionType().contains("PRE-INSPECTION")) {
//                        preCpr = preCprRepo.findByApplicationNo(sched.get(i).getApplicationId());
//                        if (preCpr.size() > 0) {
//                            model.setData(preCpr);
//                            model.setMessage("Data found");
//                            model.setStatus(true);
//                            model.setStatus_code(200);
//                        } else {
//                            model.setMessage("This Company has no schedule for Inspection");
//                            model.setStatus(false);
//                            model.setStatus_code(404);
//                        }
//                    } else if (sched.get(i).getPermitType().contains("CPR") && sched.get(i).getInspectionType().contains("POST-INSPECTION")) {
//                        postCpr = postCprRepo.findByApplicationNo(sched.get(i).getApplicationId());
//                        if (postCpr.size() > 0) {
//                            model.setData(postCpr);
//                            model.setMessage("Data found");
//                            model.setStatus(true);
//                            model.setStatus_code(200);
//                        } else {
//                            model.setMessage("This Company has no schedule for Inspection");
//                            model.setStatus(false);
//                            model.setStatus_code(404);
//                        }
//                    }
            } else {
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

}
