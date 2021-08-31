package com.board.recipes.account.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.recipes.entity.MemberRegistrationEntity;
import com.board.recipes.mapper.RegisterMemberMapper;

@Service
@Transactional
public class RegisterMemberService {

	@Autowired
	RegisterMemberMapper registMemberMapper;

	@Autowired
    PasswordEncoder passwordEncoder;

	/*
	 * 会員情報をDBに登録
	 */

	 public void registerMember(MemberRegistrationEntity entity) {

	        //パスワードをハッシュ化して、insertMemberInfo()に渡すオブジェクトにセット。
	        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

	        registMemberMapper.insertMemberInfo(entity);
	    }
}
