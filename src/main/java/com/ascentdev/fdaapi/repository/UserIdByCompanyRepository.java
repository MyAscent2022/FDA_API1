/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.repository;

import com.ascentdev.fdaapi.entity.InspectionSchedulesEntity;
import com.ascentdev.fdaapi.entity.UserIdByCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author
 * ASCENT
 */
public interface UserIdByCompanyRepository extends JpaRepository<UserIdByCompanyEntity, Integer>{
  
  UserIdByCompanyEntity findByCompanyName(String company_name);
  
}
