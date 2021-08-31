package com.board.recipes.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.recipes.models.RecipesForm;
import com.board.recipes.repository.RecipesDao;

@Service
public class RecipesService {
	@Autowired
	RecipesDao resipesDao;

	//記事作成
	public int createArticle(RecipesForm form) {
		int result = resipesDao.createArticle(form);
		return result;
	}
	//記事編集
	public int editArticle(RecipesForm form) {
		int result = resipesDao.editArticle(form);
		return result;
	}

	//論理削除
	public int deleteArticle(Integer articleId) {
		int result = resipesDao.deleteArticle(articleId);
		return result;
	}

	//一覧表示のためのデータ取得
	public List<Map<String, Object>> searchRecipesAll() {
		 List<Map<String, Object>> list = resipesDao.searchRecipesAll();
		 return list;
	}

	//記事検索
	public List<Map<String, Object>> searchRecipes(RecipesForm form){
		List<Map<String, Object>> list = resipesDao.searchRecipes(form);
		return list;
	}

	//レシピ記事詳細表示
	public List<Map<String, Object>> detailviewRecipe(Integer articleId){
		List<Map<String, Object>> list = resipesDao.detailviewRecipe(articleId);
		return list;
	}
}
