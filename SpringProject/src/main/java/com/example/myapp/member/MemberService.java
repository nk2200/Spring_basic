package com.example.myapp.member;

public class MemberService implements IMemberService {

	@Override
	public Member getMemberInfo() {
		Member member = new Member();
		member.setMemberid("1234");
		member.setName("tom");
		member.setAge(20);
		member.setEmail("1234@google.com");
		return member;
	}

}
