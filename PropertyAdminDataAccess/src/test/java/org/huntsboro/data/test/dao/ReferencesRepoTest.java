package org.huntsboro.data.test.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hamcrest.Matchers;
import org.huntsboro.data.dao.ApplicantsAndTenantsRepository;
import org.huntsboro.data.dao.ReferencesRepository;
import org.huntsboro.data.dao.RepositoryConfig;
import org.huntsboro.data.entity.ApplicantsAndTenants;
import org.huntsboro.data.entity.References;
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

public class ReferencesRepoTest {
	 private @Autowired JdbcTemplate jdbcTemplate; 
	 private @PersistenceContext EntityManager em;
	 private @Autowired ReferencesRepository repo;
	 private @Autowired ApplicantsAndTenantsRepository repo1;
	 
	@Test
    public void testInsert() {
		References obj = new References();
		//obj.setReferenceName("REF1");
		ApplicantsAndTenants ref= new ApplicantsAndTenants();
		repo1.save(ref);
		repo1.flush();
		obj.setTenant(ref);
		
		obj.setPhone("1111111");
		obj.setTenant(ref);
		
		References insertedObj = repo.save(obj);
        repo.flush();
        
        Assert.assertThat(insertedObj.getPhone(), Matchers.equalTo("1111111"));
/*	        String name = jdbcTemplate.queryForObject("SELECT cont_name FROM cont WHERE cont_id = ?", 
	                                                  String.class,
	                                                  continentInserted.getId());
	        assertThat(name, equalTo("another"));
*/	    }
	 
	 

}
