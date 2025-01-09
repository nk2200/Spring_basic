package com.example.myapp.member;

public class MemberController {
	MemberService memberService;
	
	
	
	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}



	public void pritInfo() {
		Member member = memberService.getMemberInfo();
		System.out.println(member.toString());
	}
}
