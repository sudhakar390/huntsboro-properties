/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huntsboro.admin.services;

import java.util.ArrayList;
import java.util.List;

import org.huntsboro.data.dao.RepositoryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 *
 * @author pinjasur
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"org.huntsboro.admin","org.huntsboro.data"})
//@Profile({"dev","test"})
@Import(RepositoryConfig.class)
public class ServicesConfig extends WebMvcConfigurerAdapter {
	/*private RepositoryConfig repositoryConfig;
	@Autowired
	public void setRepositoryConfig(RepositoryConfig repoConfig){
		this.repositoryConfig= repoConfig;
	}
	*/
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

	    // http
	    HttpMessageConverter converter = new StringHttpMessageConverter();
	    converters.add(converter);
	    System.out.println("HttpMessageConverter added");

	    // string
	    converter = new FormHttpMessageConverter();
	    converters.add(converter);
	    System.out.println("FormHttpMessageConverter added");

	    // json
	    converter = mappingJackson2HttpMessageConverter();
	    converters.add(converter);
	    System.out.println("MappingJackson2HttpMessageConverter added");

	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	   /* ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	    //objectMapper.registerModule(new JSR310Module());
	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	    converter.setObjectMapper(objectMapper);
	    converter.setSupportedMediaTypes(getJsonMediaTypes());
	    Jackson2DatatypeHelper.configureObjectMapper(objectMapper);
	     */
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
	            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
	            .serializationInclusion(JsonInclude.Include.NON_NULL)
	            .modulesToInstall(hibernate4Module());
		 MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
		 converter.setSupportedMediaTypes(getJsonMediaTypes());
	    return converter;
	    
	}

	private List<MediaType> getJsonMediaTypes() {
		List<MediaType> mediaTypeList= new ArrayList<MediaType>();
		mediaTypeList.add(MediaType.APPLICATION_JSON);
		mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
		mediaTypeList.add(MediaType.APPLICATION_XML);
		return mediaTypeList;
		
	}
	
	 private Hibernate4Module hibernate4Module() {
	        return new Hibernate4Module().disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
	    }
	
}
