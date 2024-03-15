/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.service;

import com.ascentdev.fdaapi.model.ApiResponseModel;
import org.springframework.stereotype.Service;

/**
 *
 * @author
 * ASCENT
 */
@Service
public interface UserService {
  ApiResponseModel getUser (String username, String password);
}
