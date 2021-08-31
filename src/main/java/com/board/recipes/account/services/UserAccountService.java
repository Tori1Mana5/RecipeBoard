package com.board.recipes.account.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.recipes.account.repository.RegisterUserAccountRepository;
import com.board.recipes.entity.MemberRegistrationEntity;

@Service
@Transactional
public class UserAccountService {

	@Autowired
	RegisterUserAccountRepository registrepositpry;

	@Autowired
	PasswordEncoder passwordEncoder;

	/*
	 * アカウント情報を登録
	 * */
	public void registerAccount(MemberRegistrationEntity user) {
		//パスワードハッシュ化して渡すオブジェクトにセット
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		registrepositpry.insertRegistUser(user);
	}

}
