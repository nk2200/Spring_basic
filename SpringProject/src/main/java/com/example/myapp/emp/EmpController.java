package com.example.myapp.emp;

public class EmpController {
	EmpService empService;
	
	public EmpController(EmpService empService) {
		this.empService = empService;
	}


	public void printInfo() {
		int count= empService.getEmpCount(50);
		System.out.println("사원의 수:"+count);
	}
}
