 
package com.spring.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.Constant;
import com.spring.domain.Constant.Code;

 
@Transactional(readOnly = true)
public interface ConstantService {

    /**
     * 
     * @param <T>
     * @param code
     * @param constantTypeClass
     * @return
     */
    public <T extends Constant> T findConstantByCode(String code,
            Class<T> constantTypeClass);

    /**
     * 
     * @param code
     * @return
     */
    public Constant findConstantByCode(Code code);


    /**
     * 
     * @param <T>
     * @param constantTypeClass
     * @return
     */
    public <T extends Constant> List<T> findConstants(Class<T> constantTypeClass);

    /**
     * 
     * @param <T>
     * @param id
     * @return
     */
    public <T extends Constant> T findConstantByPK(Long id);
    
    /**
     * 
     * @param code
     * @return
     */
    public Constant findUncachedConstantByCode(Code code);
    
}