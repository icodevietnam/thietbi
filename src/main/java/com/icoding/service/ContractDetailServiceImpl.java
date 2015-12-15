package com.icoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.ContractDetailDao;
import com.icoding.dao.GenericDao;
import com.icoding.domain.ContractDetail;

@Service
@Transactional
public class ContractDetailServiceImpl extends GenericServiceImpl<ContractDetail, Integer>
		implements ContractDetailService {

	@Autowired
	private ContractDetailDao contractDetailDao;

	@Override
	GenericDao<ContractDetail, Integer> getDao() {
		return contractDetailDao;
	}

}
