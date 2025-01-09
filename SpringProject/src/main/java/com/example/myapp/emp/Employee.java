package com.example.myapp.emp;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Employee {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private double salary;
	private double commissionPct;
	private int managerId;
	private int departmentId;
}
