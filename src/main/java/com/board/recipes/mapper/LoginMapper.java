package com.board.recipes.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.recipes.entity.Account;

@Mapper
public interface LoginMapper {
	 public Account findAccount(String name);
}
