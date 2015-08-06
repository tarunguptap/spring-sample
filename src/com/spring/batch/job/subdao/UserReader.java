
package com.spring.batch.job.subdao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;

import com.spring.service.SubService;

public class UserReader implements ItemReader<UserDTO> {

    private static int index = 0;
    private static int totalCount;
    private static int offset = 0;
    public static final int MODEL_TRANSACTION_MAX_RECORDS = 5000;
    private UserExecutionListener stepExecutionListener;
    private SubService subService;
    private Date startDate;
    private Date endDate;
    private List<UserDTO> userDTOList;
    


    public UserExecutionListener getStepExecutionListener() {
		return stepExecutionListener;
	}

	public void setStepExecutionListener(UserExecutionListener stepExecutionListener) {
		this.stepExecutionListener = stepExecutionListener;
	}

	public void init() {
        if (stepExecutionListener != null) {
            startDate = stepExecutionListener.getStartDate();
            endDate = stepExecutionListener.getEndDate();
            subService=stepExecutionListener.getSubService(); 
            totalCount = subService.getTotalCount();
            System.out.println("Total row count " + totalCount);
            userDTOList = subService.getUserDTOList();
            System.out.println("Total number of rows "
                    + userDTOList.size());
        }
    }

    @Override
    public synchronized UserDTO read() throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {
    	System.out.println(" started data reader Thread " + Thread.currentThread().getName() + "  ThreadID: "
                + Thread.currentThread().getId());
        long startTime = System.currentTimeMillis(); 
    	UserDTO modelMappingValidationDTO = null;
        if (userDTOList != null && !userDTOList.isEmpty()
                && index < userDTOList.size()) {
            modelMappingValidationDTO = userDTOList.get(index++);
            long endTime = System.currentTimeMillis();
            System.out.println(" Ended data reader Thread " + Thread.currentThread().getName() + "  ThreadID: "
                    + Thread.currentThread().getId() + " Total Time: - " + ((endTime - startTime) / 1000));
            return modelMappingValidationDTO;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(" Ended data reader Thread " + Thread.currentThread().getName() + "  ThreadID: "
                + Thread.currentThread().getId() + " Total Time: - " + ((endTime - startTime) / 1000));
        return null;
    }

}
