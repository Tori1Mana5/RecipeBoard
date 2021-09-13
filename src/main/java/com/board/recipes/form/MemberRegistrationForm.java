package com.board.recipes.form;

import javax.validation.constraints.NotNull;

//会員登録フォームで入力された内容を格納するクラス
import lombok.Data;

@Data
public class MemberRegistrationForm {
	@NotNull
	private String accountName;
	@NotNull
	private String password;
}
