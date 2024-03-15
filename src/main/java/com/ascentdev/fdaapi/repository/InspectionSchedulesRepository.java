/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.repository;

import com.ascentdev.fdaapi.entity.CompanyLtoPREListEntity;
import com.ascentdev.fdaapi.entity.InspectionSchedulesEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author
 * ASCENT
 */
public interface InspectionSchedulesRepository extends JpaRepository<InspectionSchedulesEntity, Integer> {
  
    List<InspectionSchedulesEntity> findByInspectionSchedAndStatus(Date inspection_sched, String status);
    List<InspectionSchedulesEntity> findByStatus(String status);
  InspectionSchedulesEntity findById(int schedule_id);
}
