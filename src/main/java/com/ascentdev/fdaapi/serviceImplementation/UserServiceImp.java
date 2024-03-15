/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.serviceImplementation;

import com.ascentdev.fdaapi.entity.UserEntity;
import com.ascentdev.fdaapi.error.ErrorException;
import com.ascentdev.fdaapi.model.ApiResponseModel;
import com.ascentdev.fdaapi.repository.UsersRepository;
import com.ascentdev.fdaapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author
 * ASCENT
 */
@Service
public class UserServiceImp implements UserService {
  
  @Autowired
  UsersRepository usersRepo;
  
  
  @Override
  public ApiResponseModel getUser(String username, String password) {
    
    ErrorException ex1 = null;
    ApiResponseModel model = new ApiResponseModel();
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    UserEntity userEntity = new UserEntity();
    //UserProfileEntity profileEntity = new UserProfileEntity();
    
    try {
      userEntity = usersRepo.findByUsername(username);
      
      if (userEntity == null) {
        model.setMessage("Wrong username");
        model.setStatus(false);
        model.setStatus_code(404);
      } else {
       // profileEntity = profileRepo.findById(userEntity.getProfile_id());
        if (userEntity.getPasskey().equals("null")) {
          ex1 = new ErrorException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "Wrong username", System.currentTimeMillis());
          throw ex1;
        } else {
          
          if (passwordEncoder.matches(password, userEntity.getPasskey())) {
            model.setUser_id(userEntity.getId());
            model.setRole_id(userEntity.getRoleId());
            model.setMessage("User found");
            model.setStatus(true);
            model.setStatus_code(200);
          } else {
            model.setMessage("Wrong password");
            model.setStatus(false);
            model.setStatus_code(404);
          }
          
        }
      }
      
    } catch (ErrorException ex) {
      if (ex1 == null) {
        ex1 = new ErrorException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "Bad Request", System.currentTimeMillis());
      }
      throw ex1;
    }
    return model;
  }
  
}
