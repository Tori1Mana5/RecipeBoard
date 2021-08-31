package com.board.recipes.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.board.recipes.models.RecipesForm;

@Repository
public class RecipesDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	//記事を登録
	public int createArticle(RecipesForm form) {
		//記事投稿時の本日の日程取得
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String sql = "INSERT INTO articles(user_id, delete_flag, article_title, article_body, report_date) VALUES(?, ?, ?, ?, ?)";
		Object[] param = { form.getUser_id(), 1, form.getTitle(), form.getArticle(), sdf.format(date) };
		int result = jdbcTemplate.update(sql, param);
		return result;
	}

	//記事編集
	public int editArticle(RecipesForm form) {
		String sql = "UPDATE articles SET article_title = ?, article_body = ? WHERE article_id = ?";
		Object[] param = { form.getTitle(), form.getArticle(), form.getArticle_id() };
		int result = jdbcTemplate.update(sql, param);
		return result;
	}

	//記事論理削除
	public int deleteArticle(Integer articleId) {
		String sql = "UPDATE articles SET delete_flag = ? WHERE article_id = ?";
		Object[] param = { 0, articleId };
		int result = jdbcTemplate.update(sql, param);
		return result;
	}

	//記事一覧表示
	public List<Map<String, Object>> searchRecipesAll() {

		String sql = "SELECT * FROM articles WHERE delete_flag = 1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	//記事検索
	public List<Map<String, Object>> searchRecipes(RecipesForm form) {
		String sql;
		List<Map<String, Object>> list = new ArrayList<>();

		//タイトルのみ未入力で検索
		if (form.getTitle().isEmpty() && form.getArticle().length() > 0 && form.getAcount().length() > 0
				&& form.getDate_from().length() > 0 && form.getDate_to().length() > 0) {
			System.out.println("通過0：タイトルのみ未入力で検索");
			sql = "SELECT * FROM articles WHERE article_body LIKE ? AND user_id LIKE ? AND report_date BETWEEN ? AND ?";
			Object[] param = { "%" + form.getArticle() + "%", form.getAcount(), form.getDate_from(),
					form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);
			//アカウント名のみ未入力で検索
		}
		//記事内容のみ未入力で検索
		if (form.getTitle().length() > 0 && form.getArticle().isEmpty() && form.getAcount().length() > 0
				&& form.getDate_from().length() > 0 && form.getDate_to().length() > 0) {
			System.out.println("通過1：記事内容のみ未入力で検索");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND user_id LIKE ? AND  report_date BETWEEN ? AND ?";
			Object[] param = { "%" + form.getTitle() + "%", form.getAcount(), form.getDate_from(), form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);

			//アカウント名のみ未入力で検索
		} else if (form.getTitle().length() > 0 && form.getArticle().length() > 0 && form.getAcount().isEmpty()
				&& form.getDate_from().length() > 0 && form.getDate_to().length() > 0) {
			System.out.println("通過2：アカウント名のみ未入力で検索");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND article_body LIKE ? AND report_date BETWEEN ? AND ?";
			Object[] param = { "%" + form.getTitle() + "%", "%" + form.getArticle() + "%", form.getDate_from(),
					form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);
			//投稿年月日の開始日時のみ未入力
		} else if (form.getTitle().length() > 0 && form.getArticle().length() > 0 && form.getAcount().length() > 0
				&& form.getDate_from().isEmpty() && form.getDate_to().length() > 0) {
			System.out.println("通過3：投稿年月日の開始日時のみ未入力");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND article_body LIKE ? AND user_id LIKE ? AND report_date <= ?";
			Object[] param = { "%" + form.getTitle() + "%", "%" + form.getArticle() + "%", form.getAcount(),
					form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);
			//投稿年月日の終了日時のみ未入力
		} else if (form.getTitle().length() > 0 && form.getArticle().length() > 0 && form.getAcount().length() > 0
				&& form.getDate_from().length() > 0 && form.getDate_to().isEmpty()) {
			System.out.println("通過4：投稿年月日の終了日時のみ未入力");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND article_body LIKE ? AND user_id LIKE ? AND report_date >= ?";
			Object[] param = { "%" + form.getTitle() + "%", "%" + form.getArticle() + "%", form.getAcount(),
					form.getDate_from() };
			list = jdbcTemplate.queryForList(sql, param);
			//投稿年月日のみ未入力
		} else if (form.getTitle().length() > 0 && form.getArticle().length() > 0 && form.getAcount().length() > 0
				&& form.getDate_from().isEmpty() && form.getDate_to().isEmpty()) {
			System.out.println("通過5：投稿年月日のみ未入力");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND article_body LIKE ? AND user_id LIKE ?";
			Object[] param = { "%" + form.getTitle() + "%", "%" + form.getArticle() + "%", form.getAcount() };
			list = jdbcTemplate.queryForList(sql, param);

			//3項目検索
			//タイトルと記事本文のみ未入力
		} else if (form.getTitle().isEmpty() && form.getArticle().isEmpty() && form.getAcount().length() > 0
				&& form.getDate_from().length() > 0
				&& form.getDate_to().length() > 0) {
			System.out.println("通過6：");
			sql = "SELECT * FROM articles WHERE user_id LIKE ? AND report_date BETWEEN ? AND ?";
			Object[] param = { "%" + form.getTitle() + "%", form.getDate_from(), form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);

			//タイトルとアカウント名のみ未入力
		} else if(form.getTitle().isEmpty() && form.getArticle().length() > 0 && form.getAcount().isEmpty()
				&& form.getDate_from().length() > 0
				&& form.getDate_to().length() > 0) {
			System.out.println("通過7：");
			sql = "SELECT * FROM articles WHERE AND article_body LIKE ? AND report_date BETWEEN ? AND ?";
			Object[] param = { "%" + form.getArticle() + "%", form.getDate_from(), form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);

			//タイトルと投稿年月日開始日時のみ未入力
		} else if(form.getTitle().isEmpty() && form.getArticle().length() > 0 && form.getAcount().length() > 0
				&& form.getDate_from().isEmpty()
				&& form.getDate_to().length() > 0) {
			System.out.println("通過8：");
			sql = "SELECT * FROM articles WHERE AND article_body LIKE ? AND user_id LIKE ? AND report_date <= ?";
			Object[] param = { "%" + form.getArticle() + "%", form.getAcount(), form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);

			//タイトルと投稿年月日終了日時のみ未入力
		} else if(form.getTitle().isEmpty() && form.getArticle().length() > 0 && form.getAcount().length() > 0
				&& form.getDate_from().length() > 0
				&& form.getDate_to().isEmpty()) {
			System.out.println("通過9：");
			sql = "SELECT * FROM articles WHERE AND article_body LIKE ? AND user_id LIKE ? AND report_date >= ?";
			Object[] param = { "%" + form.getArticle() + "%", form.getAcount(), form.getDate_from() };
			list = jdbcTemplate.queryForList(sql, param);
		}
			//記事本文と投稿年月日終了日のみ未入力
		else if (form.getTitle().length() > 0 && form.getArticle().isEmpty() && form.getAcount().length() > 0
				&& form.getDate_from().length() > 0
				&& form.getDate_to().isEmpty()) {
			System.out.println("通過10：");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND user_id LIKE ? AND report_date <= ?";
			Object[] param = { "%" + form.getTitle() + "%", form.getAcount(), form.getDate_from() };
			list = jdbcTemplate.queryForList(sql, param);

		}
		//記事本文とアカウント名が未入力
		else if (form.getTitle().length() > 0 && form.getArticle().isEmpty() && form.getAcount().isEmpty()
				&& form.getDate_from().length() > 0
				&& form.getDate_to().length() > 0) {
			System.out.println("通過11：");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND report_date BETWEEN ? AND ?";
			Object[] param = { "%" + form.getTitle() + "%", form.getDate_from(), form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);

		}
		//記事本文と投稿年月日開始日時のみ未入力
		else if(form.getTitle().length() > 0 && form.getArticle().isEmpty() && form.getAcount().length() > 0
				&& form.getDate_from().isEmpty()
				&& form.getDate_to().length() > 0) {
			System.out.println("通過12：");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND user_id LIKE ? AND report_date <= ?";
			Object[] param = { "%" + form.getTitle() + "%", form.getAcount(), form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);
		}
		//アカウント名と投稿年月日開始日時のみ未入力
		else if (form.getTitle().length() > 0 && form.getArticle().length() > 0 && form.getAcount().isEmpty()
				&& form.getDate_from().isEmpty()
				&& form.getDate_to().length() > 0) {
			System.out.println("通過13：");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND article_body LIKE ? AND report_date <= ?";
			Object[] param = { "%" + form.getTitle() + "%", "%" + form.getArticle() + "%", form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);

		}
		//アカウント名と投稿年月日終了日時のみ未入力
				else if (form.getTitle().length() > 0 && form.getArticle().length() > 0 && form.getAcount().isEmpty()
						&& form.getDate_from().length() > 0
						&& form.getDate_to().isEmpty()) {
					System.out.println("通過14：");
					sql = "SELECT * FROM articles WHERE article_title LIKE ? AND article_body LIKE ? AND report_date >= ?";
					Object[] param = { "%" + form.getTitle() + "%", "%" + form.getArticle() + "%", form.getDate_from() };
					list = jdbcTemplate.queryForList(sql, param);

				}
		//投稿年月日開始日時と投稿年月日終了日時のみ未入力
				else if(form.getTitle().length() > 0 && form.getArticle().length() > 0 && form.getAcount().length() > 0
						&& form.getDate_from().isEmpty()
						&& form.getDate_to().isEmpty()) {
					System.out.println("通過15：");
					sql = "SELECT * FROM articles WHERE article_title LIKE ? AND article_body LIKE ? AND user_id LIKE ?";
					Object[] param = { "%" + form.getTitle() + "%", "%" + form.getArticle() + "%", form.getAcount() };
					list = jdbcTemplate.queryForList(sql, param);
				}
		//タイトルと記事本文検索
		else if (form.getTitle().length() > 0 && form.getArticle().length() > 0) {
			System.out.println("通過16：" + form.getTitle() + " : " + form.getArticle());
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND article_body LIKE ?";
			Object[] param = {"%" + form.getTitle() + "%", "%" + form.getArticle() + "%"};
			list = jdbcTemplate.queryForList(sql, param);
			//タイトルとアカウント名検索
		} else if (form.getTitle().length() > 0 && form.getAcount().length() > 0) {
			System.out.println("通過17：");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND user_id LIKE ?";
			Object[] param = { "%" + form.getTitle() + "%", form.getAcount() };
			list = jdbcTemplate.queryForList(sql, param);
			//タイトルと投稿年月日開始日時検索
		} else if (form.getTitle().length() > 0 && form.getDate_from().length() > 0) {
			System.out.println("通過18：");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND report_date >= ?";
			Object[] param = { "%" + form.getTitle() + "%", form.getDate_from() };
			list = jdbcTemplate.queryForList(sql, param);
			//タイトルと投稿年月日終了日時検索
		} else if (form.getTitle().length() > 0 && form.getDate_to().length() > 0) {
			System.out.println("通過19：");
			sql = "SELECT * FROM articles WHERE article_title LIKE ? AND report_date <= ?";
			Object[] param = { "%" + form.getTitle() + "%", form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);
			//記事本文とアカウント名検索
		} else if(form.getArticle().length() > 0 && form.getAcount().length() > 0) {
			System.out.println("通20：");
			sql = "SELECT * FROM articles WHERE article_body LIKE ? AND user_id LIKE ?";
			Object[] param = { "%" + form.getArticle() + "%", form.getAcount() };
			list = jdbcTemplate.queryForList(sql, param);
			//記事本文と投稿年月日開始日時検索
		} else if(form.getArticle().length() > 0 && form.getDate_from().length() > 0) {
			System.out.println("通過21：");
			sql = "SELECT * FROM articles WHERE article_body LIKE ? AND report_date >= ?";
			Object[] param = { "%" + form.getArticle() + "%", form.getDate_from() };
			list = jdbcTemplate.queryForList(sql, param);
			//記事本文と投稿年月日終了日時検索
		} else if(form.getArticle().length() > 0 &&  form.getDate_to().length() > 0) {
			System.out.println("通過22：");
			sql = "SELECT * FROM articles WHERE article_body LIKE ? AND report_date <= ?";
			Object[] param = { "%" + form.getArticle() + "%", form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);
			//アカウント名と投稿年月日開始日時検索
		} else if(form.getAcount().length() > 0 && form.getDate_from().length() > 0) {
			System.out.println("通過23：");
			sql = "SELECT * FROM articles WHERE user_id LIKE ? AND report_date >= ?";
			Object[] param = { form.getAcount(), form.getDate_from() };
			list = jdbcTemplate.queryForList(sql, param);
			//アカウント名と投稿年月日終了日時検索
		}  else if(form.getAcount().length() > 0 && form.getDate_to().length() > 0) {
			System.out.println("通過24：");
			sql = "SELECT * FROM articles WHERE user_id LIKE ? AND report_date <= ?";
			Object[] param = { form.getAcount(), form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);
		}
		//記事タイトル部分一致の検索
		if (form.getTitle().length() > 0) {
			sql = "SELECT * FROM articles WHERE article_title LIKE ?";
			Object[] param = { "%" + form.getTitle() + "%" };
			list = jdbcTemplate.queryForList(sql, param);
		}
		//記事内容部分一致のみの検索
		else if (form.getArticle().length() > 0) {
			sql = "SELECT * FROM articles WHERE article_body LIKE ?";
			Object[] param = { "%" + form.getArticle() + "%" };
			list = jdbcTemplate.queryForList(sql, param);
			//アカウント名のみの検索
		} else if (form.getAcount().length() > 0) {
			sql = "SELECT * FROM articles WHERE user_id LIKE ?";
			//アカウントID完全一致で検索を実行
			Object[] param = { form.getAcount() };
			list = jdbcTemplate.queryForList(sql, param);
			//開始日付〜終了日付のみの検索
		} else if (form.getDate_from().length() > 0 && form.getDate_to().length() > 0) {
			sql = "SELECT * FROM articles WHERE report_date BETWEEN ? AND ?";
			Object[] param = { form.getDate_from(), form.getDate_to() };
			list = jdbcTemplate.queryForList(sql, param);
			//開始日時〜検索
		}  else if (form.getDate_from().length() > 0) {
			sql = "SELECT * FROM articles WHERE report_date >= ?";
			Object[] param = { form.getDate_from()};
			list = jdbcTemplate.queryForList(sql, param);
			//終了日時〜検索
		} else if (form.getDate_to().length() > 0) {
			sql = "SELECT * FROM articles WHERE report_date <= ?";
			Object[] param = { form.getDate_to()};
			list = jdbcTemplate.queryForList(sql, param);
			}
		return list;
	}

	//該当の記事の詳細を表示する
	public List<Map<String, Object>> detailviewRecipe(Integer articleId) {
		//flag条件について追加するかどうか
		String sql = "SELECT * FROM articles WHERE article_id = ?";
		Object[] param = { articleId };
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);
		return list;
	}
}
