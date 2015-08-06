package com.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.batch.job.subdao.UserDTO;
import com.spring.dao.SubDao;
import com.spring.domain.AuditInfo;
import com.spring.domain.ModelSub;
import com.spring.service.SubService;
@Service("subService")
public class SubServiceImpl implements SubService 
{
	@Autowired
	private SubDao subdao;
	
	public boolean Submission(ModelSub modsub) 
	{
		modsub.setAuditInfo(new AuditInfo());
		try
		{
			boolean result;
			result= subdao.Submission(modsub);
			return result;
			/*subdao.saveOrUpdate(modsub);
			return true;*/
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public ModelSub findRecordByPk(String userid) {
		return subdao.findRecordByPk(userid);
	}

	@Override
	public void saveOrUpdate(ModelSub modsub) {
		subdao.saveOrUpdate(modsub);
	}

	@Override
	public void saveUserRecord(ModelSub modsub) {
		ModelSub modsubNew = new ModelSub();
		modsubNew.setUid(modsub.getUid());
		modsubNew.setUname(modsub.getUname());
		subdao.saveOrUpdate(modsubNew);
	}

	@Override
	public void saveUserRecordMultiArgument(ModelSub modsub, boolean flag) {
		ModelSub modsubNew = new ModelSub();
		modsubNew.setUid(modsub.getUid());
		modsubNew.setUname(modsub.getUname());
		subdao.saveUserRecordMultiArgument(modsubNew, flag);
		
	}

	@Override
	public List<ModelSub> findRecords() {
		return subdao.findRecords();
	}

	
	public int getTotalCount(){
		return subdao.getTotalCount();
	}

	@Override
	public List<UserDTO> getUserDTOList() {
		List<UserDTO> userDTOLst = new ArrayList<UserDTO>();
		List<ModelSub> modsubLst = findRecords();
		UserDTO userDTO;
		for (ModelSub modelSub : modsubLst) {
			userDTO =  new UserDTO();
			userDTO.setUserId(modelSub.getUid());
			userDTO.setUserName(modelSub.getUname());
			userDTOLst.add(userDTO);
		}
		return userDTOLst;
	}
	
}