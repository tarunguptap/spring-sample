package test.integration.java.com.spring.common;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;




import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.dao.UserDAO;
import com.spring.domain.User;


public abstract class SpringMvcIT extends SpringIT {

    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.parseMediaType("application/json;charset=UTF-8");
    public static final MediaType TEXT_CSV_UTF8 = MediaType.parseMediaType("text/csv;charset=UTF-8");

    protected MockMvc mockMvc;

    @Autowired
    public UserDAO userDAO;

    @Autowired
    protected WebApplicationContext context;

    @Before
    public void setup() {
        OpenSessionInViewFilter openSessionInViewFilter = new OpenSessionInViewFilter();
        openSessionInViewFilter.setSessionFactoryBeanName("mySessionFactory");
        openSessionInViewFilter.setSingleSession(true);
        openSessionInViewFilter.setServletContext(context.getServletContext());

        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(openSessionInViewFilter, "/*").build();
    }

    @Before
    public void loginWithDefaultUser() {
        login(getUser(1, 1));
    }

    public void login(User user) {
        Authentication authenticationMock = mock(Authentication.class);
        when(authenticationMock.getPrincipal()).thenReturn(user);
        SecurityContextHolder.getContext().setAuthentication(authenticationMock);
    }

    public User getUser(long userId, long companyId) {
        User user = userDAO.findByPK(userId);
        return user;
    }

    @After
    public void resetDefaultUser() {
        User user = userDAO.findByPK(1L);
        if (user != null) {
            userDAO.update(user);
        }
    }

}
