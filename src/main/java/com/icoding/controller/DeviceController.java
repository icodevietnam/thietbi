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

import com.icoding.domain.Device;
import com.icoding.service.DeviceService;

@Controller
public class DeviceController extends GenericController {

	@Autowired
	private DeviceService deviceService;

	@RequestMapping(value = { "/admin/device",
			"/admin/device/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@Secured({ "ROLE_ADMIN" })
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Thiết bị");
		model.addAttribute("title", "Thiết bị");
		model.addAttribute("countNav", countNotifications());
		return "device/index";
	}

	@RequestMapping(value = "/device/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Device> getAll(Model model) {
		List<Device> listDevices = new ArrayList<Device>();
		listDevices = deviceService.getAll();
		return listDevices;
	}

	@RequestMapping(value = "/device/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDevice(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		Device device = deviceService.get(id);
		return "false";
	}

	@RequestMapping(value = "/device/new", method = RequestMethod.POST)
	@ResponseBody
	public String adddevice(@RequestParam(value = "deviceName") String deviceName,
			@RequestParam(value = "deviceDescription") String deviceDescription) {
		Device device = new Device();
		device.setName(deviceName);
		device.setDescription(deviceDescription);
		try {
			deviceService.saveOrUpdate(device);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/device/update", method = RequestMethod.POST)
	@ResponseBody
	public String updatedevice(@RequestParam(value = "deviceId") String deviceId,
			@RequestParam(value = "deviceName") String deviceName,
			@RequestParam(value = "deviceDescription") String deviceDescription) {
		Device device = deviceService.get(Integer.parseInt(deviceId));
		device.setName(deviceName);
		device.setDescription(deviceDescription);
		try {
			deviceService.saveOrUpdate(device);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/device/get", method = RequestMethod.GET)
	@ResponseBody
	public Device getdevice(@RequestParam(value = "itemId") String idemId) {
		Device device = deviceService.get(Integer.parseInt(idemId));
		return device;
	}

}
