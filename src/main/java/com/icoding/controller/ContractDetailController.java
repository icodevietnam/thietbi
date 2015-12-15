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

import com.icoding.domain.ContractDetail;
import com.icoding.service.ContractDetailService;

@Controller
public class ContractDetailController extends GenericController {

	@Autowired
	private ContractDetailService contractDetailService;

	@RequestMapping(value = { "/admin/contractDetail",
			"/admin/contractDetail/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@Secured({ "ROLE_ADMIN", "ROLE_PVC" })
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Manage ContractDetail");
		model.addAttribute("title", "Manage ContractDetail");
		model.addAttribute("countNav", countNotifications());
		return "contractDetail/index";
	}

	@RequestMapping(value = "/contractDetail/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ContractDetail> getAll(Model model) {
		List<ContractDetail> listContractDetails = new ArrayList<ContractDetail>();
		listContractDetails = contractDetailService.getAll();
		return listContractDetails;
	}

	@RequestMapping(value = "/contractDetail/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteContractDetail(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		ContractDetail contractDetail = contractDetailService.get(id);
		return "false";
	}

	@RequestMapping(value = "/contractDetail/new", method = RequestMethod.POST)
	@ResponseBody
	public String addcontractDetail(@RequestParam(value = "contractDetailName") String contractDetailName,
			@RequestParam(value = "contractDetailDescription") String contractDetailDescription) {
		ContractDetail contractDetail = new ContractDetail();
		try {
			contractDetailService.saveOrUpdate(contractDetail);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/contractDetail/update", method = RequestMethod.POST)
	@ResponseBody
	public String updatecontractDetail(@RequestParam(value = "contractDetailId") String contractDetailId,
			@RequestParam(value = "contractDetailName") String contractDetailName,
			@RequestParam(value = "contractDetailDescription") String contractDetailDescription) {
		ContractDetail contractDetail = contractDetailService.get(Integer.parseInt(contractDetailId));
		try {
			contractDetailService.saveOrUpdate(contractDetail);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/contractDetail/get", method = RequestMethod.GET)
	@ResponseBody
	public ContractDetail getcontractDetail(@RequestParam(value = "itemId") String idemId) {
		ContractDetail contractDetail = contractDetailService.get(Integer.parseInt(idemId));
		return contractDetail;
	}

}
