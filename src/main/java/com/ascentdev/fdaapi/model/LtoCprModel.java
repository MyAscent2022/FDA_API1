/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.model;

import com.ascentdev.fdaapi.entity.CompanyCprPREListEntity;
import com.ascentdev.fdaapi.entity.CompanyLtoPREListEntity;
import java.util.List;
import lombok.Data;

/**
 *
 * @author
 * ASCENT
 */
@Data
public class LtoCprModel {
  
  List<CompanyLtoPREListEntity> ltoList;
  List<CompanyCprPREListEntity> cprList;
  
}
