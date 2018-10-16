
package com.spring.batch.job.subdao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.context.SecurityContext;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.MessageSourceAccessor;

import com.spring.batch.util.SpringContextHelper;
import com.spring.exception.FatalApplicationException;
import com.spring.service.EmailService;
import com.spring.service.SubService;

public class UserExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserExecutionListener.class);

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final String MODEL_MAPPING_DATA = "model_mapping_validation_result";
    private static ApplicationContext context;
	private SubService subService;
	private EmailService emailService;
    private ExecutionContext stepExecutionCtx;
    private ExecutionContext jobExecutionCtx;
    private JobInstance jobInstance;
    private StepExecution stepExecution;
    private SecurityContext parentSecurityContext;
    private String startDateParameterValue;
    private String endDateParameterValue;
    private String siteName;
    private String fileLocation;
    private File directory;
    private File passedFile;
    private File failedFile;
    private String emailID;
    private MessageSourceAccessor messageSourceAccessor;
    private BufferedWriter failedBufferedWriter;
    private BufferedWriter passedBufferedWriter;
    private String failedTempLocation;
    private String passedTempLocation;
    private FileWriter failedFileWriter;
    private FileWriter passedFileWriter;
    private Date startDate;
    private Date endDate;
    
    public static final String FAILED_MAPPING_ITEM_ATTACHMENT_CSV = "failedMappingItemAttachment.csv";
    public static final String PASS_MAPPING_ITEM_ATTACHMENT_CSV = "passMappingItemAttachment.csv";
    public static final String PIPE_DELIMITER = "|";
    public static final String COMMA_DELIMITER = ",";

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) throws Exception {
        this.stepExecution = stepExecution;
        stepExecutionCtx = stepExecution.getExecutionContext();
        jobExecutionCtx = stepExecution.getJobExecution().getExecutionContext();
        jobInstance = stepExecution.getJobExecution().getJobInstance();
        init(jobInstance);
    }

    private void init(JobInstance jobInstance) throws Exception {
        context = SpringContextHelper.getApplicationContext();
        subService = context.getBean("subService", SubService.class);
        messageSourceAccessor = new MessageSourceAccessor(context);
        startDateParameterValue = jobInstance.getJobParameters().getString("4");
        endDateParameterValue = jobInstance.getJobParameters().getString("5");
        emailID = jobInstance.getJobParameters().getString("6");
        siteName = jobInstance.getJobParameters().getString("8");
        if (StringUtils.isNotBlank(startDateParameterValue) && StringUtils.isNotBlank(endDateParameterValue)) {
            try {
                startDate = formatter.parse(startDateParameterValue);
                endDate = formatter.parse(endDateParameterValue);
            } catch (ParseException parseException) {
                LOGGER.error(messageSourceAccessor.getMessage("error.invalid.date.format"), "yyyy-MM-dd");
                getStepExecution().setExitStatus(ExitStatus.FAILED);
                throw new Exception(messageSourceAccessor.getMessage("error.invalid.date.format"));
            }
            if (StringUtils.isBlank(emailID)) {
                LOGGER.error(messageSourceAccessor.getMessage("message.error.invalid.email.address"));
                getStepExecution().setExitStatus(ExitStatus.FAILED);
                throw new Exception(messageSourceAccessor.getMessage("message.error.invalid.email.address"));
            }

        createTempFileForEmailAttachment();
        }
    }


    private void createTempFileForEmailAttachment() {

        fileLocation = System.getProperty("java.io.tmpdir") + File.separator + MODEL_MAPPING_DATA;
        System.out.println("fileLocation "+fileLocation);
        directory = new File(fileLocation);
        if (BooleanUtils.isFalse(directory.mkdir())) {
            if (directory.exists()) {
                LOGGER.info("File already exists");
            } else {
                LOGGER.error("Failed to create directory");
                throw new FatalApplicationException("Unable to create directory for email data");
            }

        }
        failedTempLocation = fileLocation + File.separator + siteName + "_"
                + FAILED_MAPPING_ITEM_ATTACHMENT_CSV;
        passedTempLocation = fileLocation + File.separator + siteName + "_"
                + PASS_MAPPING_ITEM_ATTACHMENT_CSV;

        failedFile = new File(failedTempLocation);
        passedFile = new File(passedTempLocation);
        try {
            if (BooleanUtils.isFalse(failedFile.createNewFile()) && !failedFile.exists()
                    && BooleanUtils.isFalse(passedFile.createNewFile()) && !passedFile.exists()) {
                LOGGER.error("Failed to create file");
                throw new FatalApplicationException("Unable to create file for email data");
            } else {
                failedFileWriter = new FileWriter(failedFile);
                failedBufferedWriter = new BufferedWriter(failedFileWriter);
                passedFileWriter = new FileWriter(passedFile);
                passedBufferedWriter = new BufferedWriter(passedFileWriter);
                addCSVHeaders(failedBufferedWriter);
                addCSVHeaders(passedBufferedWriter);
            }

        } catch (IOException e) {
            LOGGER.error(messageSourceAccessor.getMessage("error.temp.file.creation"), e);
            getStepExecution().setExitStatus(ExitStatus.FAILED);
        }
    }

    private void addCSVHeaders(BufferedWriter bufferedWriter) throws IOException {
        List<String> headerTitles = new ArrayList<String>();
//        headerTitles.add(messageSourceAccessor.getMessage("label.original.model.code"));
        headerTitles.add("userid");
        headerTitles.add("username");
        String titleString = StringUtils.join(headerTitles, COMMA_DELIMITER) + "\r\n";
        bufferedWriter.write(titleString);
    }

    public ExecutionContext getStepExecutionCtx() {
        return stepExecutionCtx;
    }

    public ExecutionContext getJobExecutionCtx() {
        return jobExecutionCtx;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public BufferedWriter getBufferedWriter() {
        return failedBufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.failedBufferedWriter = bufferedWriter;
    }

    public File getFile() {
        return directory;
    }

    public StepExecution getStepExecution() {
        return stepExecution;
    }

    public void setStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    public MessageSourceAccessor getMessageSourceAccessor() {
        return messageSourceAccessor;
    }

    public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    public void setFile(File file) {
        this.directory = file;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }


    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
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

    public String getTempLocation() {
        return failedTempLocation;
    }

    public void setTempLocation(String tempLocation) {
        this.failedTempLocation = tempLocation;
    }

    public FileWriter getFileWriter() {
        return failedFileWriter;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.failedFileWriter = fileWriter;
    }

    /**
     * @return the passedFile
     */
    public File getPassedFile() {
        return passedFile;
    }

    /**
     * @param passedFile
     *            the passedFile to set
     */
    public void setPassedFile(File passedFile) {
        this.passedFile = passedFile;
    }

    /**
     * @return the failedFile
     */
    public File getFailedFile() {
        return failedFile;
    }

    /**
     * @param failedFile
     *            the failedFile to set
     */
    public void setFailedFile(File failedFile) {
        this.failedFile = failedFile;
    }

    /**
     * @return the failedBufferedWriter
     */
    public BufferedWriter getFailedBufferedWriter() {
        return failedBufferedWriter;
    }

    /**
     * @param failedBufferedWriter
     *            the failedBufferedWriter to set
     */
    public void setFailedBufferedWriter(BufferedWriter failedBufferedWriter) {
        this.failedBufferedWriter = failedBufferedWriter;
    }

    /**
     * @return the passedBufferedWriter
     */
    public BufferedWriter getPassedBufferedWriter() {
        return passedBufferedWriter;
    }

    /**
     * @param passedBufferedWriter
     *            the passedBufferedWriter to set
     */
    public void setPassedBufferedWriter(BufferedWriter passedBufferedWriter) {
        this.passedBufferedWriter = passedBufferedWriter;
    }

    /**
     * @return the failedTempLocation
     */
    public String getFailedTempLocation() {
        return failedTempLocation;
    }

    /**
     * @param failedTempLocation
     *            the failedTempLocation to set
     */
    public void setFailedTempLocation(String failedTempLocation) {
        this.failedTempLocation = failedTempLocation;
    }

    /**
     * @return the passedTempLocation
     */
    public String getPassedTempLocation() {
        return passedTempLocation;
    }

    /**
     * @param passedTempLocation
     *            the passedTempLocation to set
     */
    public void setPassedTempLocation(String passedTempLocation) {
        this.passedTempLocation = passedTempLocation;
    }

    /**
     * @return the failedFileWriter
     */
    public FileWriter getFailedFileWriter() {
        return failedFileWriter;
    }

    /**
     * @param failedFileWriter
     *            the failedFileWriter to set
     */
    public void setFailedFileWriter(FileWriter failedFileWriter) {
        this.failedFileWriter = failedFileWriter;
    }

    /**
     * @return the passedFileWriter
     */
    public FileWriter getPassedFileWriter() {
        return passedFileWriter;
    }

    /**
     * @param passedFileWriter
     *            the passedFileWriter to set
     */
    public void setPassedFileWriter(FileWriter passedFileWriter) {
        this.passedFileWriter = passedFileWriter;
    }


    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

	public SubService getSubService() {
		return subService;
	}



}
