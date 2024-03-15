/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.repository;

import com.ascentdev.fdaapi.entity.RequiredFilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ASCENT
 */
public interface RequiredFilesRepository extends JpaRepository<RequiredFilesEntity, Long>{
    
}
