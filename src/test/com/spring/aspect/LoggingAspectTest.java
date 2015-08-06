package test.com.spring.aspect;

import javax.annotation.Resource;

import static org.mockito.Mockito.when;

import org.aspectj.lang.JoinPoint;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.spring.aspect.LoggingAspect;

import test.com.spring.common.BaseTest;

public class LoggingAspectTest extends BaseTest {

	@InjectMocks
	private LoggingAspect loggingAspect = new LoggingAspect();
	
	@Mock
	private JoinPoint jointPoint;
	
	@Resource
	private MessageSource messageSource;
	
	@BeforeClass
	protected void setUp() {
//	    super.setup();
	}
	
	@BeforeMethod
	public void initialize() throws Exception {
	    MockitoAnnotations.initMocks(this);
	    loggingAspect.setMessageSource(messageSource);
	}
	
	@Test
	public void logBeforeTest() {
		Object[] args = new Object[2];
		args[0] = 1L;
		args[0] = "Password";
		when(jointPoint.getArgs()).thenReturn(args);
		loggingAspect.logBefore(jointPoint);
	}
}
