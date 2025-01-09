package com.example.myapp.hr.dao;

import java.util.List;
import java.util.Map;

import com.example.myapp.hr.model.Emp;

public interface IEmpRepository {
	int getEmpCount();
	int getEmpCount(int deptid);
	List<Emp> getEmpList();
	Emp getEmpInfo(int empid);
	List<Map<String, Object>> getEmpListMap();
	Map<String, Object> getEmpInfoMap(int empid);
	
	void updateEmp(Emp emp);
	void insertEmp(Emp emp);
	void deleteJobHistory(int empid);
	int deleteEmp(int empid, String email);
	
	List<Map<String, Object>> getAllDeptId();
	List<Map<String, Object>> getAllJobId();
	List<Map<String, Object>> getAllManagerId();
}
