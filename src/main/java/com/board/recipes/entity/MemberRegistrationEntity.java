package com.board.recipes.entity;

import lombok.Data;
//DB保存するアカウント情報を格納
@Data
public class MemberRegistrationEntity {

	private String name;

	private String password;

}
