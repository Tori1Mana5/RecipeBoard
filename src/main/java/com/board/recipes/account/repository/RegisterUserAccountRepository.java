package com.board.recipes.account.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.board.recipes.entity.MemberRegistrationEntity;

@Repository
public class RegisterUserAccountRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	//アカウント登録を行う
	public int insertRegistUser(MemberRegistrationEntity user) {
		String sql = "INSERT INTO user(name, password) VALUES (?, ?)";
		Object[] param = { user.getName(), user.getPassword()};
		int result = jdbcTemplate.update(sql, param);
		return result;
	}
}
