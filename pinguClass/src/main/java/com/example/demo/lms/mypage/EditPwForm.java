package com.example.demo.lms.mypage;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditPwForm {
		
	@NotEmpty(message = "비밀번호를 입력해주세요.")
	private String prePassword;
		
	@NotEmpty(message = "비밀번호를 입력해주세요.")
	private String password;
		
	@NotEmpty(message = "비밀번호 확인을 입력해주세요.")
	private String password2;
}