package com.lok.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * For servlet 3.0, this class is required to boot rest APIs
 * 
 * Refer: https://www.packtpub.com/books/content/restful-services-jax-rs-20
 * @author USER
 *
 */
@ApplicationPath("/rest")
public class RestApplication extends Application { }
