package com.spring.dao;


import java.util.List;

import com.spring.domain.ModelSub;

public interface SubDao extends DAO<ModelSub,Long>
{
	public boolean Submission(ModelSub modsub)throws Exception;
	
	// Created this method for testNg purpose
	public ModelSub findRecordByPk(String userid);
	
	// Created this method for testNg purpose
	public void saveUserRecordMultiArgument(ModelSub modsub, boolean flag);
	
	public List<ModelSub> findRecords();
	
	public int getTotalCount();
	
}
