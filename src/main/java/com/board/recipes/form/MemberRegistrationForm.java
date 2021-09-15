package com.board.recipes.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//会員登録フォームで入力された内容を格納するクラス
import lombok.Data;

@Data
public class MemberRegistrationForm {
	@NotNull
	@Size(min=1, max=15)
	private String accountName;
	@NotNull
	@Size(min=1, max=40)
	private String password;
}
