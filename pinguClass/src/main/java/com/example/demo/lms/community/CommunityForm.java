package com.example.demo.lms.community;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityForm {
	@NotEmpty(message = "제목은 필수할목입니다.")
	@Size(max = 200)
	private String title;

	@NotEmpty(message = "내용은 필수할목입니다.")
	private String content;
}
