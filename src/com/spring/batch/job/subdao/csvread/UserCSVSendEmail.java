package com.spring.batch.job.subdao.csvread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.activation.FileDataSource;

import org.apache.commons.collections.map.MultiValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.spring.bean.EmailAttachmentBean;
import com.spring.service.EmailService;
import com.spring.util.Constants;

/*
 * Last steps to send successful or failure item mail.
 */
public class UserCSVSendEmail implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(UserCSVSendEmail.class);

    private UserCSVExecutionListener stepExecutionListener;
    private String fileLocation;
    private File file;
    private MultiValueMap failedRecords;
    private MultiValueMap successfulRecords;
    private EmailService emailService;
    private String fileName;
    private final String SUBJECT = "ModelSub Processing Results From File : ";
    private String emailID;

    public UserCSVExecutionListener getStepExecutionListener() {
        return stepExecutionListener;
    }

    public void setStepExecutionListener(UserCSVExecutionListener stepExecutionListener) {
        this.stepExecutionListener = stepExecutionListener;
    }

    public void init() {
        if (stepExecutionListener != null) {
            fileLocation = stepExecutionListener.getFileLocation();
            file = stepExecutionListener.getFile();
            failedRecords = stepExecutionListener.getFailedRecords();
            successfulRecords = stepExecutionListener.getSuccessfulRecords();
            emailService = stepExecutionListener.getEmailService();
            emailID = stepExecutionListener.getEmailID();
            fileName = stepExecutionListener.getFileName();
        }
    }

    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception, IOException {

        logger.info("Inside Sending email - Started");
        logger.info("==================Failed Map Size=================== " + failedRecords.size());
        logger.info("==================Success Map Size==================" + successfulRecords.size());

        if (!failedRecords.isEmpty() || !successfulRecords.isEmpty()) {
            try {
                String emailMessage = null;
                String failTempLocation = fileLocation + File.separator + Constants.FAILED_RECORD_FILE;
                String sucessTempLocation = fileLocation + File.separator
                        + Constants.PASSED_RECORD_FILE;
                EmailAttachmentBean fileAttachmentFailed = new EmailAttachmentBean();
                EmailAttachmentBean fileAttachmentSuccessful = new EmailAttachmentBean();
                FileDataSource fileDataSourceFail = new FileDataSource(failTempLocation);
                FileDataSource fileDataSourceSuccessful = new FileDataSource(sucessTempLocation);
                writeRecordDeviceInformation(failedRecords, failTempLocation);
                writeRecordDeviceInformation(successfulRecords, sucessTempLocation);

                fileAttachmentFailed.setFileName(Constants.FAILED_RECORD_FILE);
                fileAttachmentSuccessful.setFileName(Constants.PASSED_RECORD_FILE);
                fileAttachmentFailed.setMimeType("text/csv");
                fileAttachmentSuccessful.setMimeType("text/csv");
                fileAttachmentFailed.setDataSource(fileDataSourceFail);
                fileAttachmentSuccessful.setDataSource(fileDataSourceSuccessful);
                emailMessage = "Total Unprocessed Records : " + failedRecords.values().size()
                        + Constants.COMMA_DELIMITER_SPACE + "Total Processed Records : "
                        + successfulRecords.values().size();

                emailService.sendEmail(emailID, "NO_REPLY_EMAIL", SUBJECT + fileName, emailMessage, fileAttachmentFailed, fileAttachmentSuccessful);
                loggedFileDelete(fileDataSourceFail.getFile().delete());
                loggedFileDelete(fileDataSourceSuccessful.getFile().delete());
                loggedFileDelete(file.delete());
            } catch (Exception exception) {
                logger.error("Exception occurred while sending email", exception);
            }
        }
        logger.info("Inside Sending email - Ended");
        return RepeatStatus.FINISHED;
    }

    private void writeRecordDeviceInformation(MultiValueMap records, String location) throws IOException {
        FileWriter fileWriter = new FileWriter(location);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Iterator<Integer> itr = records.keySet().iterator();
        while (itr.hasNext()) {

            Integer key = itr.next();
            List<String> messages = (List<String>) records.get(key);
            for (String message : messages) {
                bufferedWriter.append(message);
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
    
    private void loggedFileDelete(boolean isfileDeleted) {
        if (!isfileDeleted) {
            logger.error("Cannot delete the file");
        }
    }

}
