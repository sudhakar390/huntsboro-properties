package org.huntsboro.data.test.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hamcrest.Matchers;
import org.huntsboro.data.dao.RepositoryConfig;
import org.huntsboro.data.dao.UnitsRepository;
import org.huntsboro.data.entity.Units;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


@ContextConfiguration(classes={RepositoryConfig.class,TestConfig.class})
@ActiveProfiles("test")
@Transactional
@EnableTransactionManagement
@RunWith(SpringJUnit4ClassRunner.class)

public class UnitsRepoTest {
	 private @Autowired JdbcTemplate jdbcTemplate; 
	 private @PersistenceContext EntityManager em;
	 private @Autowired UnitsRepository repo;
	 
	 @Test
    public void testInsert() {
		 Units obj = new Units();
		obj.setCity("AUSTIN");
		
		Units insertedObj = repo.save(obj);
        repo.flush();
        
        Assert.assertThat(insertedObj.getCity(), Matchers.equalTo("AUSTIN"));
/*	        String name = jdbcTemplate.queryForObject("SELECT cont_name FROM cont WHERE cont_id = ?", 
	                                                  String.class,
	                                                  continentInserted.getId());
	        assertThat(name, equalTo("another"));
*/	    }
	 
	 

}
