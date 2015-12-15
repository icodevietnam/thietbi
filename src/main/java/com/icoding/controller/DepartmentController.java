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

import com.icoding.domain.Department;
import com.icoding.service.DepartmentService;

@Controller
public class DepartmentController extends GenericController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = { "/admin/department",
			"/admin/department/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@Secured({ "ROLE_ADMIN", "ROLE_PVC" })
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Manage Department");
		model.addAttribute("title", "Manage Department");
		model.addAttribute("countNav", countNotifications());
		return "department/index";
	}

	@RequestMapping(value = "/department/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Department> getAll(Model model) {
		List<Department> listDepartments = new ArrayList<Department>();
		listDepartments = departmentService.getAll();
		return listDepartments;
	}

	@RequestMapping(value = "/department/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDepartment(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		Department department = departmentService.get(id);
		return "false";
	}

	@RequestMapping(value = "/department/new", method = RequestMethod.POST)
	@ResponseBody
	public String adddepartment(@RequestParam(value = "departmentName") String departmentName,
			@RequestParam(value = "departmentDescription") String departmentDescription) {
		Department department = new Department();
		department.setName(departmentName);
		department.setDescription(departmentDescription);
		try {
			departmentService.saveOrUpdate(department);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/department/update", method = RequestMethod.POST)
	@ResponseBody
	public String updatedepartment(@RequestParam(value = "departmentId") String departmentId,
			@RequestParam(value = "departmentName") String departmentName,
			@RequestParam(value = "departmentDescription") String departmentDescription) {
		Department department = departmentService.get(Integer.parseInt(departmentId));
		department.setName(departmentName);
		department.setDescription(departmentDescription);
		try {
			departmentService.saveOrUpdate(department);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/department/get", method = RequestMethod.GET)
	@ResponseBody
	public Department getdepartment(@RequestParam(value = "itemId") String idemId) {
		Department department = departmentService.get(Integer.parseInt(idemId));
		return department;
	}

}
