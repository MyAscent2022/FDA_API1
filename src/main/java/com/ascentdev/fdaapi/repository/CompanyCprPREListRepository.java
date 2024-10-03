/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.repository;

import com.ascentdev.fdaapi.entity.CompanyCprPREListEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author
 * ASCENT
 */
public interface CompanyCprPREListRepository extends JpaRepository<CompanyCprPREListEntity, Integer>{
  
  List<CompanyCprPREListEntity> findByUserId(int user_id);
//  List<CompanyCprPREListEntity> findByInspectorId(int inspector_id);
  List<CompanyCprPREListEntity> findByApplicationNo(String application_no);
  
}
