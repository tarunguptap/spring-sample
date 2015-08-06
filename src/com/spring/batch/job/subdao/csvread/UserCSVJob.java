package com.spring.batch.job.subdao.csvread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationContext;

import com.spring.batch.util.JobContextHelper;


public class UserCSVJob {
    public static JobRepository jobRepository;

    private static Logger logger = LoggerFactory.getLogger(UserCSVJob.class);

    public static void main(String[] args) {

        logger.info("Before Calling Job");

        ApplicationContext context = JobContextHelper.getApplicationContext();
        //Job job = context.getBean(args[0], Job.class);
        Job job = context.getBean("userCSVJob", Job.class);
        JobLauncher jobLauncher = JobContextHelper.getApplicationContext().getBean("jobLauncher", JobLauncher.class);
        logger.info("autoProcessDeviceJob bean found");
        JobParametersBuilder jobParameters = new JobParametersBuilder();
        //jobParameters.addString("corePoolSize", args[1]); // 20
        //jobParameters.addString("maxPoolSize", args[2]); // 20
        //jobParameters.addString("resource", args[3]); // file:C:\\Test.csv
        //jobParameters.addString("emailID", args[4]);
        jobParameters.addString("corePoolSize", "20"); // 20
        jobParameters.addString("maxPoolSize", "20"); // 20
        jobParameters.addString("resource", "file:E:\\Test.csv"); // file:C:\\Test.csv
        jobParameters.addString("emailID", "tgupta@qasource.com");

        jobParameters.addLong("time", System.currentTimeMillis()).toJobParameters();
        try {
            long startTime = System.currentTimeMillis();

            JobExecution execution = jobLauncher.run(job, jobParameters.toJobParameters());
            long endTime = System.currentTimeMillis();

            logger.info("Total  Time " + (endTime - startTime) / 1000);

            logger.info("Exit Status : " + execution.getStatus());

        } catch (Exception e) {
            logger.error("Exception Occurred while executing Batch Job", e.getMessage());
        }

        logger.info("Job Completed");

    }

    public void setJobRepository(JobRepository jobRepository) {
    	UserCSVJob.jobRepository = jobRepository;
    }

}
