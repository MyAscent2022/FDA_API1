/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Carlo
 */

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new BasicAuthenInterceptor());
  }
  
}