/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascentdev.fdaapi.configuration;

import static com.ascentdev.fdaapi.utils.Conf.getPassword;
import static com.ascentdev.fdaapi.utils.Conf.getUsername;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author Carlo
 */
public class BasicAuthenInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String authHeader = request.getHeader("Authorization");

    if (request.getRequestURI().contains("view_checklist_image")) {
      return true;
    } else {

      if (authHeader != null) {

        String usernameAndPassHash = authHeader.split(" ")[1];
        String usernameAndPass = new String(Base64.getDecoder().decode(usernameAndPassHash));
        String username = usernameAndPass.split(":")[0];
        String password = usernameAndPass.split(":")[1];

        if (username.equalsIgnoreCase(getUsername()) && password.equals(getPassword())) {
          return true;
        } else {
          response.sendError(401, "Unauthorized");
          return false;
        }
      } else {
        response.sendError(401, "Unauthorized");
        return false;
      }
    }
  }

}
