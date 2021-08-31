package com.board.recipes.models;
//検索、記事登録フォームで入力された値を格納するクラス
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RecipesForm {

	@NotBlank
	@Size(min=1, max=30)
	private String title;

	@NotBlank
	@Size(min=1, max=200)
	private String article;

	private Integer article_id;

	private Integer user_id;

	private String acount;

	private String date_from;

	private String date_to;
}
