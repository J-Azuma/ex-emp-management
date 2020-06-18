package jp.co.sample.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/** 
 * 従業員情報を操作する.
 * 
 * @author junpei.azuma
 *
 */
@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/** 
	 * 従業員情報を全件検索する.
	 * 
	 * @return 従業員情報を要素に持つリスト
	 */
	public List<Employee> showList() {
		return employeeRepository.findAll();
	}
	
	/**
	 * 従業員の詳細情報を取得.
	 * 
	 * @param id 従業員ID
	 * @return 従業員詳細ページ
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}
	
	/**
	 * 従業員情報を更新する.
	 * 
	 * @param employee 従業員
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
				          
	}
	
}
