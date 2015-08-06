package com.spring.batch.job.subdao.csvread;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemWriter;

import com.spring.domain.ModelSub;

/*
 * This class needs to re-factor to updated CSV file success message 
 */
public class UserCSVWriter implements ItemWriter<ModelSub> {

    private static final Logger LOG = Logger.getLogger(ModelSub.class);
    private StepExecution stepExecution;

    @Override
    public void write(List<? extends ModelSub> ModelSub) throws Exception {
        LOG.info("Inside Writer");
    }
}
