package com.icoding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icoding.domain.DeviceType;
import com.icoding.service.DeviceTypeService;

@Controller
public class DeviceTypeController extends GenericController {

	@Autowired
	private DeviceTypeService deviceTypeService;

	@RequestMapping(value = { "/admin/deviceType",
			"/admin/deviceType/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@Secured({ "ROLE_ADMIN", "ROLE_PVC" })
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Loại Thiết Bị");
		model.addAttribute("title", "Loại Thiết Bị");
		return "deviceType/index";
	}

	@RequestMapping(value = "/deviceType/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<DeviceType> getAll(Model model) {
		List<DeviceType> listDeviceTypes = new ArrayList<DeviceType>();
		listDeviceTypes = deviceTypeService.getAll();
		return listDeviceTypes;
	}

	@RequestMapping(value = "/deviceType/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDeviceType(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		DeviceType deviceType = deviceTypeService.get(id);
		return "false";
	}

	@RequestMapping(value = "/deviceType/new", method = RequestMethod.POST)
	@ResponseBody
	public String adddeviceType(@RequestParam(value = "deviceTypeName") String deviceTypeName,
			@RequestParam(value = "deviceTypeDescription") String deviceTypeDescription) {
		DeviceType deviceType = new DeviceType();
		deviceType.setName(deviceTypeName);
		deviceType.setDescription(deviceTypeDescription);
		try {
			deviceTypeService.saveOrUpdate(deviceType);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/deviceType/update", method = RequestMethod.POST)
	@ResponseBody
	public String updatedeviceType(@RequestParam(value = "deviceTypeId") String deviceTypeId,
			@RequestParam(value = "deviceTypeName") String deviceTypeName,
			@RequestParam(value = "deviceTypeDescription") String deviceTypeDescription) {
		DeviceType deviceType = deviceTypeService.get(Integer.parseInt(deviceTypeId));
		deviceType.setName(deviceTypeName);
		deviceType.setDescription(deviceTypeDescription);
		try {
			deviceTypeService.saveOrUpdate(deviceType);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/deviceType/get", method = RequestMethod.GET)
	@ResponseBody
	public DeviceType getdeviceType(@RequestParam(value = "itemId") String idemId) {
		DeviceType deviceType = deviceTypeService.get(Integer.parseInt(idemId));
		return deviceType;
	}

}
