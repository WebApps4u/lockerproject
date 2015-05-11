package com.lok.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.TracingConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

import com.lok.rest.lockerservice.UploadService;

@ApplicationPath("/upload/test")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        // Resources.
        packages(UploadService.class.getPackage().getName());

        // MVC.
        register(JspMvcFeature.class);

        // Logging.
        register(LoggingFilter.class);

        // Tracing support.
        property(ServerProperties.TRACING, TracingConfig.ON_DEMAND.name());
    }
    
	/*public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(UploadService.class);
        classes.add(JspMvcFeature.class);
        classes.add(LoggingFilter.class);
        // Register my custom provider.
      //  classes.add(SecurityRequestFilter.class);
        // Register resources.
        //classes.add(...);
        return classes;
    }*/
}
