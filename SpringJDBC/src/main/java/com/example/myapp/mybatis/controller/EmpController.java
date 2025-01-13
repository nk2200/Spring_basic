package com.example.myapp.mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myapp.hr.model.Emp;
import com.example.myapp.hr.service.IEmpService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class EmpController {
	@Autowired
	IEmpService empService;
	
	@GetMapping({"/mybatis/count","/mybatis/cnt"})
	public String empCount(@RequestParam(value="deptid",required=false,
	defaultValue = "0") int deptid,Model model) {
		if(deptid==0) { //모든 사원의 수
			int result = empService.getEmpCount(); //biz()호출
			model.addAttribute("count",result);			
		}else {
			int result = empService.getEmpCount(deptid);
			model.addAttribute("count",result);
		}
		
		return "mybatis/count";    //WEB-INF/views/mybatis/count.jsp
	}

	@GetMapping("/mybatis/list")
	public String getAllEmps(Model model) {
		List<Emp> empList = empService.getEmpList();
//		System.out.println(empList);
		model.addAttribute("empList", empList);
		return "mybatis/list";
		
	}
	
	@GetMapping("/mybatis/{employeeId}")
	public String getEmpInfo(@PathVariable int employeeId, Model model) {
		
		try {
			Emp emp = empService.getEmpInfo(employeeId);
			model.addAttribute("emp", emp);			
		}catch(EmptyResultDataAccessException e	){
			throw new RuntimeException("사원이 없습니다");
		}
		return "mybatis/view";
	}
	
	@GetMapping("/mybatis/insert")
	public String insertEmp(Model model) {
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		System.out.println(empService.getAllDeptId());
		return "mybatis/insertform";
	}
	
	@PostMapping("/mybatis/insert")
	public String insertEmp(Emp emp, RedirectAttributes redirectAttrs) {
		try {
			System.out.println(emp.toString());
			empService.insertEmp(emp);
			redirectAttrs.addFlashAttribute("message", emp.getEmployeeId()+"번 사원의 정보가 입력되었습니다.");
			
		}catch(RuntimeException e) {
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/mybatis/list";
	}
	
	@GetMapping("/mybatis/update")
	public String updateEmp(int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		
		return "mybatis/updateform";
	}
	
	@PostMapping("/mybatis/update")
	public String updateEmp(RedirectAttributes redirectAttr, Emp emp) {
		try {
			empService.updateEmp(emp);
			redirectAttr.addFlashAttribute("message", emp.getEmployeeId()+"번 사원정보가 수정되었습니다.");
		}catch(RuntimeException e) {
			redirectAttr.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/mybatis/"+emp.getEmployeeId();
	}
	@GetMapping("/mybatis/delete")
	public String deleteEmp(int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		return "mybatis/deleteform";
	}
	@PostMapping("/mybatis/delete")
	public String deleteEmp(int empid, String email, RedirectAttributes rdAttr, Model model) {
		System.out.println(empid+", "+email);
		try {
			int deleteRow = empService.deleteEmp(empid, email);
			if(deleteRow>0) {
				System.out.println("success");
				rdAttr.addFlashAttribute("message", empid+"번 사원 딜리트 완료");
				return "redirect:/mybatis/list";
			}else {
				System.out.println("fail");
				model.addAttribute("message", "id 또는 email이 다릅니다.");
				model.addAttribute("emp", empService.getEmpInfo(empid));
				return "mybatis/deleteform";
			}
		}catch(RuntimeException e) {
			System.out.println("exception");
			model.addAttribute("message", e.getMessage());
			return "mybatis/deleteform";
		}
	}
	
	//예외처리
	@ExceptionHandler({RuntimeException.class})
	public String runtimeException(HttpServletRequest request, Exception ex, Model model) {
		model.addAttribute("exception", ex);
		model.addAttribute("url", request.getRequestURL());
		return "error/runtime";
	}
}
