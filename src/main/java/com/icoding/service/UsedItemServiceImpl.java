package com.icoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.UsedItemDao;
import com.icoding.dao.GenericDao;
import com.icoding.domain.UsedItem;

@Service
@Transactional
public class UsedItemServiceImpl extends GenericServiceImpl<UsedItem, Integer> implements UsedItemService {

	@Autowired
	private UsedItemDao usedItemDao;

	@Override
	GenericDao<UsedItem, Integer> getDao() {
		return usedItemDao;
	}

}
