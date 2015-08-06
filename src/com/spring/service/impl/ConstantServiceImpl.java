 
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


    /*
     * (non-Javadoc)
     * 
     * @see com.flipswap.service.impl.ConstantService#findConstantByCode(java.lang.String,
     *      java.lang.Class)
     */
    public <T extends Constant> T findConstantByCode(String code,
            Class<T> constantTypeClass) {
        return constantDAO.findConstantByCode(code, constantTypeClass);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.flipswap.service.ConstantService#findConstantByCode(com.flipswap.domain.Constant.Code)
     */
    public Constant findConstantByCode(Code code) {
        return constantDAO.findConstantByCode(code.toString(), Constant.class);
    }



    /*
     * (non-Javadoc)
     * 
     * @see com.flipswap.service.impl.ConstantService#findConstants(java.lang.Class)
     */
    public <T extends Constant> List<T> findConstants(Class<T> constantTypeClass) {
        return constantDAO.findConstants(constantTypeClass);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.flipswap.service.ConstantService#findConstantByPK(java.lang.Long)
     */
    public <T extends Constant> T findConstantByPK(Long id) {
        return (T) constantDAO.findByPK(id);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.flipswap.service.ConstantService#findRefreshedConstantByCode(com.flipswap.domain.Constant.Code)
     */
    public Constant findUncachedConstantByCode(Code code) {
        return constantDAO.findConstantByCode(code.toString(), Constant.class);
    }

    
}