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

  List<CompanyLtoPREListEntity> findByUserId(int userId);
//  List<CompanyLtoPREListEntity> findByInspectorId(int inspectorId);
  CompanyLtoPREListEntity findByApplicationNo(String application_no);
  
  
  
  
}
