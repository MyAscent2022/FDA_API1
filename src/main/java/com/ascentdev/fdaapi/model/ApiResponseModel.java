/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.model;

import java.util.List;
import lombok.Data;

/**
 *
 * @author ASCENT
 */
@Data
public class ApiResponseModel {
  String message;
  boolean status;
  int status_code;
  int role_id;
  int user_id;
  List<?> data, schedData, fullSchedData;
//  LtoCprModel model;
}
