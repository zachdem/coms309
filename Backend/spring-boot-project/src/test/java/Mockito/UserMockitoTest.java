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
		
		User user = new User(1,"testnetid","testpassword","first","last","testusername",1234,1234,1234);
		when(repo.findByNetid("testnetid")).thenReturn(user);
		
		
		assertEquals("success",userService.validateUserService(user));
		
	}
	
	@Test
	public void signUpExistingUserTest()
	{
		User user = new User(1,"testnetid","testpassword","first","last","testusername",1234,1234,1234);
		when(repo.findByNetid("testnetid")).thenReturn(user);
		
		assertEquals("user_already_exists", userService.signUpUserService(user));
	}
	

}
