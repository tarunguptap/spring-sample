package com.spring.batch.job.subdao.csvread;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.item.file.LineCallbackHandler;

import com.spring.util.Constants;

/* Callback class for CSV File header verification.
 * 
 */
public class UserCSVFileHeader implements LineCallbackHandler {
    private static final Logger logger = LoggerFactory.getLogger(UserCSVFileHeader.class);

    private static final String[] HEADERS = { Constants.USERID, Constants.USERNAME };

    private UserCSVExecutionListener stepExecutionListener;

    @Override
    public void handleLine(String headerLine) {
        logger.info("Inside CSV file header  - Started");
        System.out.println("Inside CSV file header  - Started");

        if (StringUtils.isBlank(headerLine)) {
            logger.error("File should not be empty");
            System.out.println("File should not be empty");
            stepExecutionListener.getStepExecution().setExitStatus(ExitStatus.FAILED);
        }

        String[] token = headerLine.split(",");
        Boolean invalidHeaderFlag = false;
        StringBuilder errorMsgBuilder = new StringBuilder();

        if (token.length != HEADERS.length) {
            if (token.length == 1) {
                invalidHeaderFlag = true;
                logger.error("Invalid Headers. Please verify headers should be comma separated");
                errorMsgBuilder.append("Invalid Headers. Please verify headers should be comma separated");
            } else {
                invalidHeaderFlag = true;
                logger.error("Invalid Number of Headers, Actual Headers: " + token.length + " Expected Headers: "
                        + HEADERS.length);
                errorMsgBuilder.append("Invalid Number of Headers, Actual Headers: " + token.length
                        + " Expected Headers: " + HEADERS.length);
            }
        } else {
            for (int i = 0; i < token.length; i++) {
                if ((StringUtils.length(token[i]) != StringUtils.length(HEADERS[i]))
                        || (!StringUtils.equals(token[i], HEADERS[i]))) {
                    invalidHeaderFlag = true;
                    if (StringUtils.isBlank(token[i])) {
                        int j = i + 1;
                        errorMsgBuilder.append("Missing Header at column " + j + ", Expected Header : " + HEADERS[i]);
                    } else {
                        errorMsgBuilder.append("Invalid Header, Actual Headers : " + token[i] + " Expected Header : "
                                + HEADERS[i]);
                    }
                    errorMsgBuilder.append("\n");
                }
            }
        }
        if (invalidHeaderFlag) {
            logger.error("Invalid headers are provided." + errorMsgBuilder.toString());
            System.out.println("Invalid headers are provided." + errorMsgBuilder.toString());
            stepExecutionListener.getStepExecution().setExitStatus(ExitStatus.FAILED);
        }

        logger.info("Inside CSV file header - Ended");
    }

    public UserCSVExecutionListener getStepExecutionListener() {
        return stepExecutionListener;
    }

    public void setStepExecutionListener(UserCSVExecutionListener stepExecutionListener) {
        this.stepExecutionListener = stepExecutionListener;
    }

}
