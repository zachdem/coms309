package Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cyrun.springbootstarter.user.UserRepository;
import cyrun.springbootstarter.user.UserService;
import cyrun.springbootstarter.user.User;


public class UserMockitoTest {
	
	public UserMockitoTest(){
		
	}
	
	@Mock 
	UserRepository repo;
	
	@InjectMocks
	UserService userService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getUserByNetIDTest() {
		
		User user = new User(1,"testnetid","testpassword","first","last","testusername",1234,1234,1234,500.0);
		when(repo.findByNetid("testnetid")).thenReturn(user);
		
		
		assertEquals("success",userService.validateUserService(user));
		
	}
	
	@Test
	public void signUpExistingUserTest()
	{
		User user = new User(1,"testnetid","testpassword","first","last","testusername",1234,1234,1234,500.0);
		when(repo.findByNetid("testnetid")).thenReturn(user);
		
		assertEquals("user_already_exists", userService.signUpUserService(user));
	}
	
	
	/**
	 * Test that verifies getUserBalance function in UserService
	 */
	@Test
	public void getUserBalanceTest() {
		String netid = "test_netid";
		User user = new User(1, "testnetid", "testpassword", "first", "last", "testusername", 1234, 1234, 1234, 500.0);
		when(repo.findByNetid(netid)).thenReturn(user);
		
		//Call the service for checking balance
		String balanceStr = userService.getUserBalance(netid);
		
		double balanceDec = Double.valueOf(balanceStr);
		
		assertEquals(500.0, balanceDec, 0);		
	}
	
	
	/**
	 * Test that verifies verifyUserExists function in UserService
	 */
	@Test
	public void verifyUserExistsTest() {
		User user1 = new User(1, "testnetid1", "testpassword", "first", "last", "testusername", 1234, 1234, 1234, 500.0);
		User user2 = new User(1, "testnetid2", "testpassword", "first", "last", "testusername", 1234, 1234, 1234, 500.0);

		when(repo.findByNetid(user1.getNetid())).thenReturn(user1);
		
		Boolean result = userService.verifyUserExists(user1);
		
		assertEquals(true, result);
		
		result = userService.verifyUserExists(user2);
		
		assertEquals(false, result);
	}


}
