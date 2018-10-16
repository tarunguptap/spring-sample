 
package com.spring.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.ConstantDAO;
import com.spring.domain.Constant;
import com.spring.domain.Constant.Code;
import com.spring.service.ConstantService;

 
@Service("constantService")
public class ConstantServiceImpl implements ConstantService {
    @Resource
	private ConstantDAO constantDAO;


    public <T extends Constant> T findConstantByCode(String code,
            Class<T> constantTypeClass) {
        return constantDAO.findConstantByCode(code, constantTypeClass);
    }

   
    public Constant findConstantByCode(Code code) {
        return constantDAO.findConstantByCode(code.toString(), Constant.class);
    }



   
    public <T extends Constant> List<T> findConstants(Class<T> constantTypeClass) {
        return constantDAO.findConstants(constantTypeClass);
    }

    
    public <T extends Constant> T findConstantByPK(Long id) {
        return (T) constantDAO.findByPK(id);
    }    
  
    public Constant findUncachedConstantByCode(Code code) {
        return constantDAO.findConstantByCode(code.toString(), Constant.class);
    }

    
}
