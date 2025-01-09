package com.example.myapp.hr;

import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.example.myapp.hr.model.Emp;
import com.example.myapp.hr.service.IEmpService;

public class EmpMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = new GenericXmlApplicationContext("application-config.xml");
		IEmpService empService = context.getBean(IEmpService.class);
		System.out.println("-- 사원수 조회");
		System.out.println("모든 사원의 수: " + empService.getEmpCount());
		System.out.println("50번 부서 사원의 수: " + empService.getEmpCount(50));

		System.out.println("--	103번	사원의	정보를	조회합니다.");
		System.out.println(empService.getEmpInfoMap(103));

		System.out.println("--	사원	전체	정보를	조회합니다.");
		System.out.println(empService.getEmpListMap());

		/*
		 * System.out.println("--새로운 사원정보를 입력합니다."); Emp emp = new Emp();
		 * emp.setEmployeeId(210); emp.setFirstName("tom"); emp.setLastName("Holland");
		 * emp.setEmail("tommy"); emp.setPhoneNumber("222-222"); emp.setHireDate(new
		 * java.sql.Date(System.currentTimeMillis())); emp.setJobId("IT_PROG");
		 * emp.setSalary(8000); emp.setCommissionPct(0.2); emp.setManagerId(100);
		 * emp.setDepartmentId(10); try { empService.insertEmp(emp);
		 * System.out.println("insert OK"); }catch(Exception e) {
		 * System.out.println(e.getMessage()); }
		 */

		System.out.println("-- 신규 사원의 정보를 조회/출력합니다.");
		Map<String, Object> emp210 = empService.getEmpInfoMap(210);
		System.out.println(emp210);

		// 수정
		Emp emp = new Emp();
		emp.setEmployeeId(Integer.valueOf(emp210.get("EMPLOYEE_ID").toString()));
		emp.setFirstName(emp210.get("FIRST_NAME").toString());
		emp.setLastName(emp210.get("LAST_NAME").toString());
		emp.setEmail(emp210.get("EMAIL").toString());
		emp.setPhoneNumber(emp210.get("PHONE_NUMBER").toString());
		emp.setHireDate(new java.sql.Date(System.currentTimeMillis()));
		emp.setJobId(emp210.get("JOB_ID").toString());
		emp.setSalary(Double.valueOf(emp210.get("SALARY").toString()));
		emp.setCommissionPct(Double.valueOf(emp210.get("COMMISSION_PCT").toString()));
		emp.setManagerId(Integer.valueOf(emp210.get("MANAGER_ID").toString()));
		emp.setDepartmentId(Integer.valueOf(emp210.get("DEPARTMENT_ID").toString()));
		System.out.println("--	210번	사원의	급여를	10%	인상합니다.");
		emp.setSalary(emp.getSalary() * 1.1); // 급여 정보 수정: 10%인상
		empService.updateEmp(emp);
		System.out.println("--	수정된	사원의	정보를	조회/출력합니다.");
		emp210 = empService.getEmpInfoMap(210);
		System.out.println(emp210);

		context.close();
	}
}
