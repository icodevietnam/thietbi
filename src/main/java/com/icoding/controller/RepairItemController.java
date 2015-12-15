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

import com.icoding.domain.RepairItem;
import com.icoding.service.RepairItemService;

@Controller
public class RepairItemController extends GenericController {

	@Autowired
	private RepairItemService repairItemService;

	@RequestMapping(value = { "/admin/repairItem",
			"/admin/repairItem/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@Secured({ "ROLE_ADMIN", "ROLE_PVC" })
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Manage RepairItem");
		model.addAttribute("title", "Manage RepairItem");
		model.addAttribute("countNav", countNotifications());
		return "repairItem/index";
	}

	@RequestMapping(value = "/repairItem/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<RepairItem> getAll(Model model) {
		List<RepairItem> listRepairItems = new ArrayList<RepairItem>();
		listRepairItems = repairItemService.getAll();
		return listRepairItems;
	}

	@RequestMapping(value = "/repairItem/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteRepairItem(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		RepairItem repairItem = repairItemService.get(id);
		return "false";
	}

	@RequestMapping(value = "/repairItem/new", method = RequestMethod.POST)
	@ResponseBody
	public String addrepairItem(@RequestParam(value = "repairItemName") String repairItemName,
			@RequestParam(value = "repairItemDescription") String repairItemDescription) {
		RepairItem repairItem = new RepairItem();
		try {
			repairItemService.saveOrUpdate(repairItem);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/repairItem/update", method = RequestMethod.POST)
	@ResponseBody
	public String updaterepairItem(@RequestParam(value = "repairItemId") String repairItemId,
			@RequestParam(value = "repairItemName") String repairItemName,
			@RequestParam(value = "repairItemDescription") String repairItemDescription) {
		RepairItem repairItem = repairItemService.get(Integer.parseInt(repairItemId));
		try {
			repairItemService.saveOrUpdate(repairItem);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/repairItem/get", method = RequestMethod.GET)
	@ResponseBody
	public RepairItem getrepairItem(@RequestParam(value = "itemId") String idemId) {
		RepairItem repairItem = repairItemService.get(Integer.parseInt(idemId));
		return repairItem;
	}

}
