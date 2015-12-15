package com.icoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.DeviceTypeDao;
import com.icoding.dao.GenericDao;
import com.icoding.domain.DeviceType;

@Service
@Transactional
public class DeviceTypeServiceImpl extends GenericServiceImpl<DeviceType, Integer> implements DeviceTypeService {

	@Autowired
	private DeviceTypeDao deviceTypeDao;

	@Override
	GenericDao<DeviceType, Integer> getDao() {
		return deviceTypeDao;
	}

}
