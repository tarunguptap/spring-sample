
package com.spring.batch.job.subdao;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.support.MessageSourceAccessor;

public class UserWriter implements ItemWriter<UserDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserWriter.class);
    private UserExecutionListener stepExecutionListener;
    private BufferedWriter failedBufferedWriter;
    private BufferedWriter passedBufferedWriter;
    private MessageSourceAccessor messageSourceAccessor;
    public static final String COMMA_DELIMITER = ",";
    public static final String CSV_CARRIAGE_RETURN = "\n";

    public UserExecutionListener getStepExecutionListener() {
        return stepExecutionListener;
    }

    public void setStepExecutionListener(UserExecutionListener stepExecutionListener) {
        this.stepExecutionListener = stepExecutionListener;
    }

    public void init() {
        if (stepExecutionListener != null) {
            messageSourceAccessor = stepExecutionListener.getMessageSourceAccessor();
            failedBufferedWriter = stepExecutionListener.getFailedBufferedWriter();
            passedBufferedWriter = stepExecutionListener.getPassedBufferedWriter();
        }
    }

    @Override
    public void write(List<? extends UserDTO> userDTOList) throws Exception {
        List<String> passModelColumnsContent = new ArrayList<String>();
        List<String> failModelcolumnsContent = new ArrayList<String>();
        try {
            for (UserDTO userDTO : userDTOList) {
                    passModelColumnsContent.add(StringEscapeUtils.escapeCsv(StringUtils
                            .trimToEmpty(userDTO.getUserId())));
                    passModelColumnsContent.add(StringEscapeUtils.escapeCsv(StringUtils
                            .trimToEmpty(userDTO.getUserName())));
                    String contentString = StringUtils.join(passModelColumnsContent, COMMA_DELIMITER)
                            + CSV_CARRIAGE_RETURN;
                    passedBufferedWriter.write(contentString);
                    passedBufferedWriter.flush();
                    passModelColumnsContent.clear();
                    
                    
                    failModelcolumnsContent.add(StringEscapeUtils.escapeCsv(StringUtils
                            .trimToEmpty(userDTO.getUserId())));
                    failModelcolumnsContent.add(StringEscapeUtils.escapeCsv(StringUtils
                            .trimToEmpty(userDTO.getUserName())));
                    String failContentString = StringUtils.join(failModelcolumnsContent, COMMA_DELIMITER)
                            + CSV_CARRIAGE_RETURN;
                    failedBufferedWriter.write(failContentString);
                    failedBufferedWriter.flush();
                    failModelcolumnsContent.clear();
            }
        } catch (Exception e) {
            LOGGER.error(messageSourceAccessor.getMessage("error.data.writing"), e);
            stepExecutionListener.getStepExecution().setExitStatus(ExitStatus.FAILED);
        }
    }

}
