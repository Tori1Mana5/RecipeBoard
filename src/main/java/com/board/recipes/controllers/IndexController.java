package com.board.recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.recipes.account.services.RegisterMemberService;
import com.board.recipes.entity.MemberRegistrationEntity;
import com.board.recipes.form.MemberRegistrationForm;

@Controller
public class IndexController {

	@Autowired
	private RegisterMemberService registMemberService;

	/**
	 * 会員情報入力画面に遷移する。
	 */
	@RequestMapping("/registrationForm")
	public String showRegistMemberForm(Model model,  MemberRegistrationForm memberRegistrationForm) {

		model.addAttribute(new MemberRegistrationForm());

		//会員情報入力画面。
		return "registrationForm";
	}

	/*
	 * アカウント登録
	 */
	@RequestMapping("/register")
	public String registerUser(@ModelAttribute @Validated MemberRegistrationForm memberRegistrationForm,
			BindingResult result, Model model) {

		//未入力バリデーション
		if(result.hasErrors()) {
			System.out.println("test");
			return showRegistMemberForm(model, memberRegistrationForm);
		}
		//USERテーブルにinsertする時の引数。
		MemberRegistrationEntity entity = new MemberRegistrationEntity();

		entity.setName(memberRegistrationForm.getAccountName());
		entity.setPassword(memberRegistrationForm.getPassword());

		//USERテーブルにinsertする。
		registMemberService.registerMember(entity);

		return "result";
	}

}
