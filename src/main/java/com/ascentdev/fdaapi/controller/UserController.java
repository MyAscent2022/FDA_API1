/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.controller;


import com.ascentdev.fdaapi.error.ErrorException;
import com.ascentdev.fdaapi.error.ErrorObject;
import com.ascentdev.fdaapi.model.ApiResponseModel;
import com.ascentdev.fdaapi.serviceImplementation.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author
 * Carlo
 * Dela
 * Pena
 */
@RestController
@RequestMapping("/fda_mobile/")
public class UserController {

  @Autowired
  UserServiceImp userServiceimp;

  @GetMapping("user_login")
  public ApiResponseModel getUser(@RequestParam("username") String username, @RequestParam("password") String password) {
    return userServiceimp.getUser(username, password);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorObject> handlereException(ErrorException ex) {
    ErrorObject eObject = new ErrorObject(ex.getStatus_code(), ex.getMessage(), ex.getTimestamp());
    return new ResponseEntity<ErrorObject>(eObject, ex.getStatus());
  }
}
