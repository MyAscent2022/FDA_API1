/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author ascen
 */
@Data
public class ErrorException extends RuntimeException{
  
  String message;
  HttpStatus status;
  int status_code;
  long timestamp;
  public ErrorException(int status_code,HttpStatus status,String message,long timestamp){
     super();
    this.status_code=status_code;
    this.status=status;
    this.message=message;
    this.timestamp=timestamp;
   
  }
}
