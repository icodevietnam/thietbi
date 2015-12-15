package com.icoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.RepairItemDao;
import com.icoding.dao.GenericDao;
import com.icoding.domain.RepairItem;

@Service
@Transactional
public class RepairItemServiceImpl extends GenericServiceImpl<RepairItem, Integer> implements RepairItemService {

	@Autowired
	private RepairItemDao repairItemDao;

	@Override
	GenericDao<RepairItem, Integer> getDao() {
		return repairItemDao;
	}

}
