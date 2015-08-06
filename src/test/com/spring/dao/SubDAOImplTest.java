package test.com.spring.dao;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.com.spring.common.BaseTest;

import com.spring.dao.SubDao;
import com.spring.domain.ModelSub;


/**
 * @author tarun gupta
 */
public class SubDAOImplTest extends BaseTest {
	@Resource
	private SubDao subdao;


    @Test(dataProvider = "getModelObject")
    public void Submission(ModelSub modelSub, boolean expectedResult, String userid) throws Exception {
    	Assert.assertEquals(expectedResult,
    			subdao.Submission(modelSub), "Error occured while saving the object");
    	ModelSub mdsb = subdao.findRecordByPk(userid);
    	Assert.assertEquals(userid, mdsb.getUid(),"Error occured while fetching the object");

    }
    
    @DataProvider
	protected Object[][] getModelObject() {
    	ModelSub modelSub1 = new ModelSub("1","tarun1");
    	ModelSub modelSub2 = new ModelSub("2","tarun2");
    	ModelSub modelSub3 = new ModelSub("3","tarun3");
    	ModelSub modelSub4 = new ModelSub("4","tarun4");
    	
    	return new Object[][] {{modelSub1,true, modelSub1.getUid()}, {modelSub2,true, modelSub2.getUid()}, 
				{modelSub3,true, modelSub3.getUid()},{modelSub4,true, modelSub4.getUid()}};
    }
}
