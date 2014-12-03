package com.lok.database;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.lok.model.PartyRecord;
import com.lok.service.PartyRecordService;
import com.lok.service.impl.PartyRecordServiceImpl;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/lok/config/PartyRecordServiceTests-context.xml")
public class PartyRecordServiceTests {

	/* @Configuration
	    static class ContextConfiguration {

	        // this bean will be injected into the BookShootServiceTest class
	        @Bean
	        public BookShootService bookShootService() {
	        	BookShootService bookShootService = new BookShootServiceImpl();
	            // set properties, etc.
	            return bookShootService;
	        }
	    }
*/	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PartyRecordService partyRecordService;

	@Test
	@Transactional
	public void testSaveOrderWithItems() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		
		PartyRecord bookingShoot = new PartyRecord();
		partyRecordService.save(bookingShoot);
		
		System.out.println(" Test Pass");
		
		session.flush();
//		assertNotNull(order.getId());
	}



}
