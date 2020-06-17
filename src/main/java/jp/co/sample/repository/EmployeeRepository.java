package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/** 従業員情報を扱うリポジトリ.
 * 
 * @author junpei.azuma
 *
 */
@Repository
public class EmployeeRepository {
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
			Employee employee = new Employee();
			employee.setId(rs.getInt("id"));
			employee.setName(rs.getString("name"));
			employee.setImage(rs.getString("image"));
			employee.setGender(rs.getString("gender"));
			employee.setHireDate(rs.getDate("hire_date"));
			employee.setMailAddress(rs.getString("mail_address"));
			employee.setZipCode(rs.getString("zip_code"));
			employee.setAddress(rs.getString("address"));
			employee.setTelephone(rs.getString("telephone"));
			employee.setSalary(rs.getInt("salary"));
			employee.setCharacteristics(rs.getString("characteristics"));
			employee.setDependentsCount(rs.getInt("dependents_count"));
			return employee;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/** 従業員を全件検索.
	 * @return 従業員リスト
	 */
	public List<Employee> findAll() {
		String sql = "select id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count"
				   + " from employees order by hire_date desc;";
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		return employeeList;
		
		}
	
	/** idで従業員を検索する.
	 * 
	 * @param id 
	 * @return idが一致する従業員
	 */
	public Employee load(Integer id) {
		String sql = "select id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count"
				   + " from employees where id = :id;";
		MapSqlParameterSource param = new  MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		
		return employee;
	}
	
	/** 従業員情報を更新する.
	 * @param employee 従業員情報
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String sql = "update employees set(name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count)"
				   + " values(:name, :image, :gender, :hire_date, :mail_address, :zip_code, :address, :telephone, :salary, :characteristics, :dependents_count)"
				   + "where id = :id;";
		template.update(sql, param);
		
		
				     
	}
}
