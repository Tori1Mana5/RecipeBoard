package com.board.recipes.models;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
//記事投稿フォームで入力された内容を格納するクラス
@Data
public class RecipesForm {

	@Size(min=1, max=30)
	private String title;

	@Size(min=1, max=200)
	private String article;

	private MultipartFile fileDate;

	private Integer article_id;

	private Integer user_id;

	private String acount;

	private String date_from;

	private String date_to;
}
