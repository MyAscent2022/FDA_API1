/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author
 * ASCENT
 */
public class Conf {

  private static String getPropertyValue(String propName) {
    Properties prop = new Properties();
    String returnValue = "";
    try {
      prop.load(new FileInputStream("C:\\FDA_CONF\\conf\\config.properties"));
      returnValue = prop.getProperty(propName);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return returnValue;
  }


  public static String getUsername() {
    return getPropertyValue("api.uname");
  }

  public static String getPassword() {
    return getPropertyValue("api.pword");
  }

}
