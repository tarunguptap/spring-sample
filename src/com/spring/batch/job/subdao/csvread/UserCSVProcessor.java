package com.spring.batch.job.subdao.csvread;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.security.core.context.SecurityContext;
import org.apache.commons.collections.map.MultiValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.support.MessageSourceAccessor;

import com.spring.domain.ModelSub;
import com.spring.service.SubService;

public class UserCSVProcessor implements ItemProcessor<ModelSub, ModelSub> {
    private static final Logger logger = LoggerFactory.getLogger(UserCSVProcessor.class);

    private UserCSVExecutionListener stepExecutionListener;
    private String fileLocation;
    private File file;
    private MultiValueMap failedRecords;
    private MultiValueMap successfulRecords;
    private String fileName = "";
    private String baseURL;
    private static AtomicInteger rowNo = new AtomicInteger(0);
    private SecurityContext parentSecurityContext;
    private MessageSourceAccessor messageSourceAccessor;
    private SubService subService;

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
            fileName = stepExecutionListener.getFileName();
            baseURL = stepExecutionListener.getBaseURL();
            parentSecurityContext = stepExecutionListener.getParentSecurityContext();
            messageSourceAccessor = stepExecutionListener.getMessageSourceAccessor();
            subService=stepExecutionListener.getSubService();
        }
    }

    @Override
    public ModelSub process(ModelSub modelSub) throws Exception {

        logger.info(" started Processing Thead " + Thread.currentThread().getName());
        long startTime = System.currentTimeMillis();
        try {
        	subService.saveOrUpdate(modelSub);

                    successfulRecords.put(
                            rowNo.incrementAndGet(), modelSub.getUname());
        } catch (Exception exception) {
            logger.error("Exception : for modelsub : " + modelSub.getUname(), exception);

            failedRecords.put(
                    rowNo.incrementAndGet(),
                    modelSub.getUname());
        }
        long endTime = System.currentTimeMillis();
        logger.info(" Ended Processing Thead " + Thread.currentThread().getName() + " Total Time: - "
                + ((endTime - startTime) / 1000));
        return modelSub;
    }

    public MultiValueMap getFailedRecords() {
        return stepExecutionListener.getFailedRecords();
    }

    public MultiValueMap getSuccessfulRecords() {
        return stepExecutionListener.getSuccessfulRecords();
    }

}
