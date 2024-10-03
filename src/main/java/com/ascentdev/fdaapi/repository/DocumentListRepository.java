/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ascentdev.fdaapi.repository;

import com.ascentdev.fdaapi.entity.DocumentListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
public interface DocumentListRepository extends JpaRepository<DocumentListEntity, Integer> {
  DocumentListEntity findByName(String name);
}
