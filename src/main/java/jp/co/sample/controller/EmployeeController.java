package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を操作する.
 * 
 * @author junpei.azuma
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}

	/**
	 * 従業員情報を格納したリストを取得する.
	 * 
	 * @param model リクエストスコープを使うためのオブジェクト
	 * @return 従業員リスト表示画面
	 */
	@RequestMapping("showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list.html";
	}

	/**
	 * 従業員詳細を表示.
	 * 
	 * @param id 従業員ID
	 * @param model リクエストスコープを使うためのオブジェクト
	 * @return 従業員詳細表示ページ
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		model.addAttribute("employee",  employeeService.showDetail(Integer.parseInt(id)));
		return "employee/detail.html";
	}

}
