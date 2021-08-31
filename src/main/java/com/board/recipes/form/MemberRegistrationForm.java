package com.board.recipes.form;

//会員登録フォームで入力された内容を格納するクラス
import lombok.Data;

@Data
public class MemberRegistrationForm {
	private String accountName;

	private String password;
}
