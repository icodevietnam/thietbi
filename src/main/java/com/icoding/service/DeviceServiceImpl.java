package com.icoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.DeviceDao;
import com.icoding.dao.GenericDao;
import com.icoding.domain.Device;

@Service
@Transactional
public class DeviceServiceImpl extends GenericServiceImpl<Device, Integer> implements DeviceService {

	@Autowired
	private DeviceDao deviceDao;

	@Override
	GenericDao<Device, Integer> getDao() {
		return deviceDao;
	}

}
