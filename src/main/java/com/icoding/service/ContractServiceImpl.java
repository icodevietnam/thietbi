package com.icoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.ContractDao;
import com.icoding.dao.GenericDao;
import com.icoding.domain.Contract;

@Service
@Transactional
public class ContractServiceImpl extends GenericServiceImpl<Contract, Integer> implements ContractService {

	@Autowired
	private ContractDao contractDao;

	@Override
	GenericDao<Contract, Integer> getDao() {
		return contractDao;
	}

}
