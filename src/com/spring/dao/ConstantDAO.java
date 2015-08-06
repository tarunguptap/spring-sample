 
package com.spring.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.domain.Constant;

 
public interface ConstantDAO extends DAO<Constant, Long> {

    public <T extends Constant> T findConstantByCode(String code,
            Class<T> codeTypeClass) throws DataAccessException;

    public <T extends Constant> List<T> findConstants(Class<T> codeTypeClass)
            throws DataAccessException;
    
}