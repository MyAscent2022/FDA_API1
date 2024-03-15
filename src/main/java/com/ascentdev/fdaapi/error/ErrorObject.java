/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.error;

import lombok.Data;

/**
 *
 * @author ascen
 */
@Data
public class ErrorObject {
  private int status_code;
  private String message;
  private long timestamp;

  public ErrorObject(int status_code, String message, long timestamp) {
    this.status_code = status_code;
    this.message = message;
    this.timestamp = timestamp;
  }
  
  
  
  
  
  
}
