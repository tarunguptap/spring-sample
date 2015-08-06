package test.com.spring.dataprovider;

import org.testng.annotations.DataProvider;

import com.spring.domain.ModelSub;

public class DataProviderSource {
	
	@DataProvider
	public static Object[][] getModelSubObjects() {
		ModelSub modelSub = new ModelSub("1","tarun1");
    	return new Object[][] {{modelSub, modelSub.getUid()}};

	}
}
