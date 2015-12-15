package com.icoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.DepartmentDao;
import com.icoding.dao.GenericDao;
import com.icoding.domain.Department;

@Service
@Transactional
public class DepartmentServiceImpl extends GenericServiceImpl<Department, Integer> implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	GenericDao<Department, Integer> getDao() {
		return departmentDao;
	}

}
