package com.spring.batch.job.subdao.csvread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.spring.domain.ModelSub;
import com.spring.util.Constants;

/* Callback class for CSV ROW Mapper
 * 
 */
public class UserCSVFieldMapper implements FieldSetMapper<ModelSub> {

    private static Logger logger = LoggerFactory.getLogger(UserCSVFieldMapper.class);
    private String siteName = "";
    private String userName = "";
    private String fileName = "";
    private UserCSVExecutionListener stepExecutionListener;

    @Override
    public ModelSub mapFieldSet(FieldSet field) throws BindException {
        logger.info("Inside CSV Row Mapper - Started");
        System.out.println("Inside CSV Row Mapper - Started");

        String userId = field.readString(Constants.USERID);
        String userName = field.readString(Constants.USERNAME);
        System.out.println("userId "+userId+"userName "+userName);
        ModelSub modelSub = new ModelSub(userId,userName);
        
        
        logger.info("Inside CSV Row Mapper - Ended");
        System.out.println("Inside CSV Row Mapper - Ended");

        return modelSub;
    }

    public UserCSVExecutionListener getStepExecutionListener() {
        return stepExecutionListener;
    }

    public void setStepExecutionListener(UserCSVExecutionListener stepExecutionListener) {
        this.stepExecutionListener = stepExecutionListener;
    }

    public void init() {

        if (stepExecutionListener != null) {
            siteName = stepExecutionListener.getSiteName();
            userName = stepExecutionListener.getUserName();
            fileName = stepExecutionListener.getFileName();
        }
    }

}
