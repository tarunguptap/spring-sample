

package com.spring.batch.job.subdao;

import java.io.BufferedWriter;
import java.io.File;
import java.util.Date;

import javax.activation.FileDataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.spring.bean.EmailAttachmentBean;
import com.spring.service.EmailService;


public class UserSendEMail implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(UserSendEMail.class);
    private static final String EMAIL_TEXT = "Model Mapping Validation Result";
    private EmailService emailService;
    private String siteName;
    private UserExecutionListener stepExecutionListener;
    private Date startDate;
    private Date endDate;
    private File directory;
    private String emailId;
    private BufferedWriter failedBufferedWriter;
    private BufferedWriter passBufferedWriter;
    private String emailSubject;

    private String failedTempLocation;
    private String passedTempLocation;
    
    public static final String FAILED_MAPPING_ITEM_ATTACHMENT_CSV = "failedMappingItemAttachment.csv";
    public static final String PASS_MAPPING_ITEM_ATTACHMENT_CSV = "passMappingItemAttachment.csv";

    public UserExecutionListener getStepExecutionListener() {
        return stepExecutionListener;
    }

    public void setStepExecutionListener(UserExecutionListener stepExecutionListener) {
        this.stepExecutionListener = stepExecutionListener;
    }

    public void init() {
        if (stepExecutionListener != null) {
            directory = stepExecutionListener.getFile();
            emailId = stepExecutionListener.getEmailID();
            startDate = stepExecutionListener.getStartDate();
            endDate = stepExecutionListener.getEndDate();
            emailService = stepExecutionListener.getEmailService();
            siteName = stepExecutionListener.getSiteName();
            failedBufferedWriter = stepExecutionListener.getFailedBufferedWriter();
            passBufferedWriter = stepExecutionListener.getPassedBufferedWriter();
            failedTempLocation = stepExecutionListener.getFailedTempLocation();
            passedTempLocation = stepExecutionListener.getPassedTempLocation();

        }
    }

    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
        failedBufferedWriter.close();
        passBufferedWriter.close();

        EmailAttachmentBean fileAttachmentFailed = new EmailAttachmentBean();
        EmailAttachmentBean fileAttachmentSuccessful = new EmailAttachmentBean();
        FileDataSource failedFileDataSource = new FileDataSource(failedTempLocation);
        FileDataSource passedFileDataSource = new FileDataSource(passedTempLocation);

        fileAttachmentFailed.setFileName(FAILED_MAPPING_ITEM_ATTACHMENT_CSV);
        fileAttachmentSuccessful.setFileName(PASS_MAPPING_ITEM_ATTACHMENT_CSV);
        fileAttachmentFailed.setMimeType("text/csv");
        fileAttachmentSuccessful.setMimeType("text/csv");
        fileAttachmentFailed.setDataSource(failedFileDataSource);
        fileAttachmentSuccessful.setDataSource(passedFileDataSource);
        emailSubject = StringUtils.upperCase(siteName) + " : Model Mapping Validation from " + startDate + " to "
                + endDate;

        //emailService.sendEmail(emailId, "NO_REPLY_EMAIL",
                //emailSubject, EMAIL_TEXT, fileAttachmentFailed, fileAttachmentSuccessful);
       // loggedFileDelete(failedFileDataSource.getFile().delete());
       //loggedFileDelete(passedFileDataSource.getFile().delete());
       // loggedFileDelete(directory.delete());
        return RepeatStatus.FINISHED;
    }

    private void loggedFileDelete(boolean isfileDeleted) {
        if (!isfileDeleted) {
            logger.error("Cannot delete the file");
        }
    }
}
