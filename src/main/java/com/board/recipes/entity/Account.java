package com.board.recipes.entity;

import lombok.Data;

//DBから取得した情報を格納するクラス
@Data
public class Account {

	private String name;

	private String password;

}
