package com.spring.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.spring.batch.job.subdao.UserDTO;
import com.spring.domain.ModelSub;

@Transactional(readOnly = true)
public interface SubService 
{
	@Transactional(readOnly = false)
	public boolean Submission(ModelSub modsub);
	
	// Created this method for testNg purpose
	public ModelSub findRecordByPk(String userid);
	
	// Created this method for testNg purpose
	@Transactional(readOnly = false)
	public void saveOrUpdate(ModelSub modsub);
	
	// Created this method for testNg purpose
	public void saveUserRecord(ModelSub modsub);
	
	// Created this method for testNg purpose
	public void saveUserRecordMultiArgument(ModelSub modsub, boolean flag);
	
	public List<ModelSub> findRecords();
	
	public int getTotalCount();
	
	public List<UserDTO> getUserDTOList();
}