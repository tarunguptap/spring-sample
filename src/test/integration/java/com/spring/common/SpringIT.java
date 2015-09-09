package test.integration.java.com.spring.common;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/test-context.xml" })
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, ServletTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "flipswapDataSource")
public abstract class SpringIT {

    @Before
    public void cleanup() {
        resetDateTime();
    }

    public void setTime(DateTime dateTime) {
        DateTimeUtils.setCurrentMillisFixed(dateTime.getMillis());
    }

    private void resetDateTime() {
        DateTimeUtils.setCurrentMillisSystem();
    }
}
