package com.spring.batch.job.subdao.csvread;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContext;

import com.spring.batch.util.SpringContextHelper;
import com.spring.exception.FatalApplicationException;
import com.spring.service.EmailService;
import com.spring.service.SubService;


public class UserCSVExecutionListener {
    private static Logger logger = LoggerFactory.getLogger(UserCSVExecutionListener.class);
    private static ApplicationContext context;
    private SubService subService;
	private EmailService emailService;
    private String siteName;
    private String userName;
    private ExecutionContext stepExecutionCtx;
    private ExecutionContext jobExecutionCtx;
    private JobInstance jobInstance;
    private String fileLocation;
    private File file;
    private MultiValueMap failedRecords;
    private MultiValueMap successfulRecords;
    private static String fileName;
    private static String filePath;
    private String baseURL;
    private String emailID;
    private SecurityContext parentSecurityContext;
    private StepExecution stepExecution;

    private MessageSourceAccessor messageSourceAccessor;

    public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
        stepExecutionCtx = stepExecution.getExecutionContext();
        jobExecutionCtx = stepExecution.getJobExecution().getExecutionContext();
        jobInstance = stepExecution.getJobExecution().getJobInstance();
        init(jobInstance);
    }

    public SubService getSubService() {
		return subService;
	}

	private void init(JobInstance jobInstance) {
        context = SpringContextHelper.getApplicationContext();
//        emailService = context.getBean("emailService", EmailService.class);
//        messageSourceAccessor = new MessageSourceAccessor(context.getBean("messageSource",
//                ResourceBundleMessageSource.class));
        subService = context.getBean("subService", SubService.class);
//        siteName = context.getEnvironment().getProperty("${site.name}");
        fileLocation = System.getProperty("java.io.tmpdir") + File.separator + "Auto-Process-Device-Information"
                + Calendar.getInstance().getTimeInMillis();
        file = new File(fileLocation);
        if (BooleanUtils.isFalse(file.mkdir())) {
            throw new FatalApplicationException("Unable to create directory for email data");
        }
        failedRecords = MultiValueMap.decorate(new HashMap<String, String>());
        successfulRecords = MultiValueMap.decorate(new HashMap<String, String>());
        filePath = jobInstance.getJobParameters().getString("2");
        baseURL = jobInstance.getJobParameters().getString("3");
        emailID = jobInstance.getJobParameters().getString("4");
        fileName = new File(filePath).getName();

        logger.info("Initalized Context");
    }

    public void setStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    public ExecutionContext getStepExecutionCtx() {
        return stepExecutionCtx;
    }

    public ExecutionContext getJobExecutionCtx() {
        return jobExecutionCtx;
    }

    public StepExecution getStepExecution() {
        return stepExecution;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public File getFile() {
        return file;
    }


    public EmailService getEmailService() {
        return emailService;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getUserName() {
        return userName;
    }

    public MultiValueMap getFailedRecords() {
        return failedRecords;
    }

    public MultiValueMap getSuccessfulRecords() {
        return successfulRecords;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getEmailID() {
        return emailID;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return the parentSecurityContext
     */
    public SecurityContext getParentSecurityContext() {
        return parentSecurityContext;
    }

    /**
     * @param parentSecurityContext
     *            the parentSecurityContext to set
     */
    public void setParentSecurityContext(SecurityContext parentSecurityContext) {
        this.parentSecurityContext = parentSecurityContext;
    }

    public void setFailedRecords(MultiValueMap failedRecords) {
        this.failedRecords = failedRecords;

    }

    public void setSuccessfulRecords(MultiValueMap successfulRecords) {
        this.successfulRecords = successfulRecords;

    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;

    }

    public MessageSourceAccessor getMessageSourceAccessor() {
        return this.messageSourceAccessor;
    }

}
