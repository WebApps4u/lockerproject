package com.lok.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

import com.lok.rest.lockerservice.UploadService;

/**
 * For servlet 3.0, this class is required to boot rest APIs
 * 
 * Refer: https://www.packtpub.com/books/content/restful-services-jax-rs-20
 * @author USER
 *
 */
@ApplicationPath("/rest")
public class RestApplication extends Application {
	

}
