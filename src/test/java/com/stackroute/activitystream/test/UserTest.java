package com.stackroute.activitystream.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.UserService.UserServiceApplication;
import com.stackroute.activitystream.dao.UserDao;
import com.stackroute.activitystream.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=UserServiceApplication.class)
public class UserTest {

	

	 User user;

	@Autowired
	 UserDao userDAO;

	@Before
	public  void init() {
	
		user=new User();
		
	}
	
	@Test
	public void createUserTestCase() {

		user.setName("gowtham");
		user.setEmailId("gowtham@gmail.com");
		user.setPassword("password");
		user.setAddress("coimbatur");
		user.setPhoneNo("9856853556");

		
		Boolean status = userDAO.save(user);
		assertEquals(true, status);
		
	}

	@Ignore
	@Test
	public void checkAuthentication() {
		user = userDAO.validate("sweta@gmail.com", "password");
		String actualValue = "sweta@gmail.com";
		String expectValue = user.getEmailId();
		assertEquals("Login Successfull", expectValue, actualValue);
		

	}

	@Ignore
	@Test
	public void checkAuthenticationByWrongCredentials() {
		User validatedUser = userDAO.validate("sweta@gmail.com", "p@ssword");
		assertNull(validatedUser);
	}

	@Ignore
	@Test
	public void checkAuthenticationByNullUserName() {
		User validatedUser = userDAO.validate("", "password");
		assertNull(validatedUser);
	}
	
	@Ignore
	@Test
	public void checkAuthenticationByNullPassword() {
		User validatedUser = userDAO.validate("sweta@gmail.com", "");
		assertNull(validatedUser);
	}

	
	

}
