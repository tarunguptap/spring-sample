package test.com.spring.service;

import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import test.com.spring.common.BaseTest;
import test.com.spring.dataprovider.DataProviderSource;

import com.spring.dao.SubDao;
import com.spring.domain.ModelSub;
import com.spring.service.SubService;
import com.spring.service.impl.SubServiceImpl;
/**
 * 
 * @author tgupta
 *
 */
public class SubServiceImplTest extends BaseTest {

	@InjectMocks
	private SubServiceImpl subServiceImpl = new SubServiceImpl();
	
	@Mock
	private SubDao subdao;
	
	@Resource
	private SubService subservice;

	@BeforeMethod
	public void initMocks(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(dataProvider = "getModelSubObjects", dataProviderClass=DataProviderSource.class)
	public void findRecordByPk(ModelSub modelSub, String userid) {
		// Mocking the DAO layer to avoid DB operation
		when(subdao.findRecordByPk(userid)).thenReturn(modelSub);
		Assert.assertEquals(modelSub, subServiceImpl.findRecordByPk(userid),"Error occured while fetching the object");

	}
	
	// Testcase to insert the data in db
	@Test(dataProvider = "getModelSubObjects", dataProviderClass=DataProviderSource.class)
	public void saveOrUpdateTest(ModelSub modelSub, String userid) {
		subservice.saveOrUpdate(modelSub);
		ModelSub mdsb = subservice.findRecordByPk(userid);
		Assert.assertEquals(mdsb.getUid(), modelSub.getUid(),"Error occured while fetching the object");
	}
	
	/**
	 * @param modelSub
	 * @param userid
	 *  Testcase to mock and verify the void methods. 
	 *  This testcase will be executed with the subServiceImpl bec its mocked and will not execute with subService as its not mocked
	 *  We are passing the modelSub in method parameter and we are not using the argThat as this is not required bec. we are not creating the new 
	 *  instance of modelsub in subserviceImpl method
	 */
	@Test(dataProvider = "getModelSubObjects", dataProviderClass=DataProviderSource.class)
	public void saveOrUpdateTest1(ModelSub modelSub, String userid) {
		doNothing().when(subdao).saveOrUpdate(modelSub);
		subServiceImpl.saveOrUpdate(modelSub);
		verify(subdao).saveOrUpdate(modelSub);
	}
	
	/**
	 * @param modelSub
	 * @param userid
	 *  Testcase to mock and verify the void methods. 
	 *  This testcase will be executed with the subServiceImpl bec its mocked and will not execute with subService as its not mocked
	 *  We are passing the modelSub in method parameter. Since we are creating the new instance of the model sub inside the service method saveUserRecord
	 *  So we are using the argThat other wise it will give us the "Actual invocation has different arguments" exception
	 *  
	 */
	@Test(dataProvider = "getModelSubObjects", dataProviderClass=DataProviderSource.class)
	public void saveOrUpdateTest2(final ModelSub modelSub, String userid) {
		ArgumentMatcher<ModelSub> modelSubArgumentMatcher = new ArgumentMatcher<ModelSub>() {
            @Override
            public boolean matches(Object argument) {
                if (argument != null && modelSub.getUid().equals(((ModelSub) argument).getUid()))
                    return true;
                return false;
            }
        };
		doNothing().when(subdao).saveOrUpdate(argThat(modelSubArgumentMatcher));
		subServiceImpl.saveUserRecord(modelSub);
		verify(subdao).saveOrUpdate(argThat(modelSubArgumentMatcher));
	}
	
	/**
	 * @param modelSub
	 * @param userid
	 *  Testcase to mock and verify the void methods. 
	 *  This testcase will be executed with the subServiceImpl bec its mocked and will not execute with subService as its not mocked
	 *  We are passing the modelSub in method parameter. Since we are creating the new instance of the model sub inside the service method saveUserRecordMultiArgument
	 *  So we are using the argThat other wise it will give us the "Actual invocation has different arguments" exception
	 *  Since the saveUserRecordMultiArgument method has two parameters and for one of parameter we are using the argThat so its mandatory that either to use argThat
	 *  or eq method for all other method arguments otherwise we will get the "When using matchers, all arguments have to be provided by matchers." exception
	 */
	@Test(dataProvider = "getModelSubObjects", dataProviderClass=DataProviderSource.class)
	public void saveOrUpdateTest3(final ModelSub modelSub, String userid) {
		ArgumentMatcher<ModelSub> modelSubArgumentMatcher = new ArgumentMatcher<ModelSub>() {
            @Override
            public boolean matches(Object argument) {
                if (argument != null && modelSub.getUid().equals(((ModelSub) argument).getUid()))
                    return true;
                return false;
            }
        };
		doNothing().when(subdao).saveUserRecordMultiArgument(argThat(modelSubArgumentMatcher), eq(true));
		subServiceImpl.saveUserRecordMultiArgument(modelSub, true);
		verify(subdao).saveUserRecordMultiArgument(argThat(modelSubArgumentMatcher),  eq(true));
	}
}