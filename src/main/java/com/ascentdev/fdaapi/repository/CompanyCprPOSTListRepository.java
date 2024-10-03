/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.repository;

import com.ascentdev.fdaapi.entity.CompanyCprPOSTListEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author
 * ASCENT
 */
public interface CompanyCprPOSTListRepository extends JpaRepository<CompanyCprPOSTListEntity, Integer>{
  
  List<CompanyCprPOSTListEntity> findByUserId(int user_id);
  List<CompanyCprPOSTListEntity> findByInspectorId(int inspector_id);
  List<CompanyCprPOSTListEntity> findByApplicationNo(String application_no);
  
}
