package org.huntsboro.admin.services;

import javax.servlet.Filter;

import org.huntsboro.data.dao.RepositoryConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServicesInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RepositoryConfig.class };
    }
   
    @Override
    protected Class<?>[] getServletConfigClasses() {
    	return new Class<?>[]{ServicesConfig.class};
    }
   
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
       /* Filter [] singleton = { new CORSFilter()};
        return singleton;*/
    	return null;
    }
}
