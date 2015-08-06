
package com.spring.batch.job.subdao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.spring.service.SubService;

public class UserProcessor implements
        ItemProcessor<UserDTO, UserDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProcessor.class);

    private UserExecutionListener stepExecutionListener;
    private SubService subService;

    public UserExecutionListener getStepExecutionListener() {
        return stepExecutionListener;
    }

    public void setStepExecutionListener(UserExecutionListener stepExecutionListener) {
        this.stepExecutionListener = stepExecutionListener;
    }

    public void init() {
        if (stepExecutionListener != null) {
        	subService = stepExecutionListener.getSubService();
        } else {
            LOGGER.error("Step Execution Listener is null");
        }
    }

    @Override
    public UserDTO process(UserDTO userDTO) {
        try {
            if (userDTO != null) {
                System.out.println("userDTO is not null");
            }
        } catch (Exception e) {
            LOGGER.error("Processor caught exception", e);
        }
        return userDTO;
    }
}
