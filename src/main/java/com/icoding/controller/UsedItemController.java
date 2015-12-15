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

import com.icoding.domain.UsedItem;
import com.icoding.service.UsedItemService;

@Controller
public class UsedItemController extends GenericController {

	@Autowired
	private UsedItemService usedItemService;

	@RequestMapping(value = { "/admin/usedItem",
			"/admin/usedItem/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@Secured({ "ROLE_ADMIN", "ROLE_PVC" })
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Manage UsedItem");
		model.addAttribute("title", "Manage UsedItem");
		model.addAttribute("countNav", countNotifications());
		return "usedItem/index";
	}

	@RequestMapping(value = "/usedItem/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<UsedItem> getAll(Model model) {
		List<UsedItem> listUsedItems = new ArrayList<UsedItem>();
		listUsedItems = usedItemService.getAll();
		return listUsedItems;
	}

	@RequestMapping(value = "/usedItem/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUsedItem(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		UsedItem usedItem = usedItemService.get(id);
		return "false";
	}

	@RequestMapping(value = "/usedItem/new", method = RequestMethod.POST)
	@ResponseBody
	public String addusedItem(@RequestParam(value = "usedItemName") String usedItemName,
			@RequestParam(value = "usedItemDescription") String usedItemDescription) {
		UsedItem usedItem = new UsedItem();
		try {
			usedItemService.saveOrUpdate(usedItem);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/usedItem/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateusedItem(@RequestParam(value = "usedItemId") String usedItemId,
			@RequestParam(value = "usedItemName") String usedItemName,
			@RequestParam(value = "usedItemDescription") String usedItemDescription) {
		UsedItem usedItem = usedItemService.get(Integer.parseInt(usedItemId));
		try {
			usedItemService.saveOrUpdate(usedItem);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/usedItem/get", method = RequestMethod.GET)
	@ResponseBody
	public UsedItem getusedItem(@RequestParam(value = "itemId") String idemId) {
		UsedItem usedItem = usedItemService.get(Integer.parseInt(idemId));
		return usedItem;
	}

}
