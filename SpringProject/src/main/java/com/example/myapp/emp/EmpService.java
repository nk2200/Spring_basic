package com.example.myapp.emp;

public class EmpService implements IEmpService {

	@Override
	public int getEmpCount(int deptid) {
		if(deptid==50) {
			return 5;
		}else {
			return 0;
		}
	}

}
