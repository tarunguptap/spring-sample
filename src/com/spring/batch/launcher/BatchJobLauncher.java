package com.spring.batch.launcher;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import com.spring.batch.util.JobContextHelper;

/**
 * @author 
 * @version $Id$
 */
public class BatchJobLauncher {
    private final static Logger log = LoggerFactory.getLogger(BatchJobLauncher.class);

    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {

        if (args.length == 0) { // Validate job parameters for Job Name
            throw new JobParametersInvalidException("Job parameter list can't be empty, atleast job name is required");
        }
        Job job = JobContextHelper.getApplicationContext().getBean(args[0], Job.class);
        JobParametersBuilder builder = new JobParametersBuilder();
        // TODO: Need to add current date as one of parameter to prevent
        // consecutive execution of a single job in a day
        builder.addParameter("0", new JobParameter(args[0]));
        builder.addDate("1", new Date());
        for (int i = 1; i < args.length; i++) {
            builder.addParameter(String.valueOf(i + 1), new JobParameter(args[i]));
        }

        JobLauncher launcher = JobContextHelper.getApplicationContext().getBean("jobLauncher", JobLauncher.class);

        // Execute Job
        JobExecution jobExecution = launcher.run(job, builder.toJobParameters());
        // System.out.println("ExitStatus:"+jobExecution.getExitStatus());
        log.info("Job completed with job execution id: " + jobExecution.getId());
        if (jobExecution.getExitStatus() != null
                && jobExecution.getExitStatus().equals(jobExecution.getExitStatus().COMPLETED))
            System.exit(0);
        else
            System.exit(-1);

    }
}
