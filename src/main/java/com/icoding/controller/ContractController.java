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

import com.icoding.domain.Contract;
import com.icoding.service.ContractService;

@Controller
public class ContractController extends GenericController {

	@Autowired
	private ContractService contractService;

	@RequestMapping(value = { "/admin/contract",
			"/admin/contract/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@Secured({ "ROLE_ADMIN" })
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Hợp Đồng");
		model.addAttribute("title", "Hợp Đồng");
		return "contract/index";
	}

	@RequestMapping(value = "/contract/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Contract> getAll(Model model) {
		List<Contract> listContracts = new ArrayList<Contract>();
		listContracts = contractService.getAll();
		return listContracts;
	}

	@RequestMapping(value = "/contract/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteContract(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		Contract contract = contractService.get(id);
		return "false";
	}

	@RequestMapping(value = "/contract/new", method = RequestMethod.POST)
	@ResponseBody
	public String addcontract(@RequestParam(value = "contractName") String contractName,
			@RequestParam(value = "contractDescription") String contractDescription) {
		Contract contract = new Contract();
		contract.setName(contractName);
		contract.setDescription(contractDescription);
		try {
			contractService.saveOrUpdate(contract);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/contract/update", method = RequestMethod.POST)
	@ResponseBody
	public String updatecontract(@RequestParam(value = "contractId") String contractId,
			@RequestParam(value = "contractName") String contractName,
			@RequestParam(value = "contractDescription") String contractDescription) {
		Contract contract = contractService.get(Integer.parseInt(contractId));
		contract.setName(contractName);
		contract.setDescription(contractDescription);
		try {
			contractService.saveOrUpdate(contract);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/contract/get", method = RequestMethod.GET)
	@ResponseBody
	public Contract getcontract(@RequestParam(value = "itemId") String idemId) {
		Contract contract = contractService.get(Integer.parseInt(idemId));
		return contract;
	}

}
