package com.board.recipes.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.recipes.entity.MemberRegistrationEntity;

@Mapper
public interface RegisterMemberMapper {

	//会員情報をUSERテーブルにinsertする。
	public void insertMemberInfo(MemberRegistrationEntity entity);
}