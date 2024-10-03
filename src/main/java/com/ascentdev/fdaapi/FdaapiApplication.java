package com.ascentdev.fdaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

// -- FOR JAR FILE

@SpringBootApplication
@PropertySource({"file:C:\\FDA_CONF\\conf\\config.properties"})
public class FdaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FdaapiApplication.class, args);
	}

}

// --  FOR WAR FILE

//@SpringBootApplication
//@PropertySource({"file:C:\\FDA_CONF\\conf\\config.properties"})
//public class FdaapiApplication extends SpringBootServletInitializer {
//
//  @Override
//  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//    return application.sources(FdaapiApplication.class);
//  }
//
//  public static void main(String[] args) {
//    SpringApplication.run(FdaapiApplication.class, args);
//  }
//
//}
