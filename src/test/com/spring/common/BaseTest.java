package test.com.spring.common;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spring.common.SpringContextHelper;

/**
 * @author tarun gupta
 */
@ContextConfiguration(locations = {"classpath:resources/applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class BaseTest extends AbstractTransactionalTestNGSpringContextTests {
	
	protected ApplicationContext applicationContext;
    
	private void initializeContext(){
		if(applicationContext == null){
			applicationContext = SpringContextHelper.getApplicationContextForTestNg();
		}
	}
    @BeforeClass
    protected void setup() throws Exception {
    	System.out.println("----On Setup----");
    	initializeContext();
        Assert.assertNotNull(applicationContext,"Application Context Cannot be null");
        /*String userName = constantService.findConstantByCode(Constant.Code.BATCH_USER_NAME).getValue();
        try {
            SecurityUtils.setAuthenticatedUser(userService.findUserByUsername(userName, true));
        } catch (UserNotFoundException e) {
            String message = "Could not initialize security context for user [" + userName + "].";
            throw new IllegalArgumentException(message);
        }*/
    }
    
    @Test
    public void testContext()
    {
        System.out.println("Context Initialized");
    }
    
}
