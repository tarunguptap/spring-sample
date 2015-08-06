package test.com.spring.service;

import java.util.Set;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import test.com.spring.common.BaseTest;

import com.spring.domain.Role;
import com.spring.domain.User;
import com.spring.service.UserService;
/**
 * 
 * @author tgupta
 *
 */
public class UserServiceImplTest extends BaseTest {

	
	@Resource
	private UserService userService;

	// Testcase to fetch the data from db
	@Test
	public void testFindUserById() {
		User user = userService.findUserById(2L);
		if(user != null) {
			Set<Role> roles = user.getRoles();
			System.out.println(roles.size());
		}
	}
}