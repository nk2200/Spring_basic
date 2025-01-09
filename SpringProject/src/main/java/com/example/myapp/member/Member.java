package com.example.myapp.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Member {
	private String memberid;
	private String name;
	private int age;
	private String email;
}
