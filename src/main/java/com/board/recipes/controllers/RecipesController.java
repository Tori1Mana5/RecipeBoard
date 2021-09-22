package com.board.recipes.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.recipes.models.RecipesForm;
import com.board.recipes.services.RecipesService;
import com.sun.istack.logging.Logger;

@Controller
public class RecipesController {

	@Autowired
	RecipesService service;

	private static final Logger log = Logger.getLogger(RecipesController.class);

	@GetMapping("/index")
	public String index(Model model) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String username = authentication.getName();
		Object principal = authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		System.out.println("アカウント名" + username);
		System.out.println("主要なデータ" + principal);
		System.out.println(authorities);

		List<Map<String, Object>> list = service.searchRecipesAll();
		model.addAttribute("recipes_list", list);
		return "index";
	}
	//削除ボタンを押下すると削除実施
	@GetMapping("/delete")
	public String deleteArticle(@RequestParam(name="id", defaultValue = "") Integer articleId) {
		service.deleteArticle(articleId);
		return "redirect:/index";
	}

	//タイトルをクリックすると詳細表示
	@GetMapping("/view")
	public String view(@RequestParam(name="id", defaultValue = "") Integer articleId,
			Model model) {
		//ログインユーザーと記事の投稿者のuser_idが合っているのかを確認する
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String username = authentication.getName();
		System.out.println("記事詳細を見ているアカウント名" + username);

		List<Map<String, Object>> list = service.detailviewRecipe(articleId);
		System.out.println(list);
		model.addAttribute("list", list);
		return "list/article";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("recipes/search")
	public String getList() {
		return "list/search";
	}
	@PostMapping("recipes/search")
	public String postArticle(RedirectAttributes attr, RecipesForm form) {
		List<Map<String, Object>> list = service.searchRecipes(form);
		attr.addFlashAttribute("list", list);
		System.out.println(list);
		return "redirect:/recipes/search";
	}
	//新規登録時と既存の記事の編集で分岐する
	@GetMapping("recipes/add")
	public String getAdd(@RequestParam(name="id", defaultValue = "") Integer articleId,
			RecipesForm form, Model model) {
			//ログインしたアカウントユーザーのidを投稿記事に紐つける
			List<Map<String, Object>> list = service.detailviewRecipe(articleId);
			SecurityContext context = SecurityContextHolder.getContext();
			Authentication authentication = context.getAuthentication();
			String username = authentication.getName();
			System.out.println(username);
			form.setUser_id(username);
			//既存の記事の場合は書かれている内容を編集できるようにする
			if(!CollectionUtils.isEmpty(list)) {
				System.out.println(list);
				form.setArticle_id(articleId);
				form.setTitle(list.get(0).get("article_title").toString());
				form.setArticle(list.get(0).get("article_body").toString());
				model.addAttribute("recipesForm", form);
			}
		return "create/add";
	}

	@PostMapping("recipe/create")
	public String Createpost(@Validated RecipesForm form, BindingResult result, Model model) {
		log.info(form.getFileDate().getName() + "," + form.getFileDate().getSize());
		System.out.println("投稿者は" + form.getUser_id());
		if(result.hasErrors()) {
			return getAdd(null, form, model);
		}
		//idがある場合は該当の記事の編集を実行
		if(form.getArticle_id() == null) {
			service.createArticle(form);
		}else {
			service.editArticle(form);
		}

		return "redirect:/index";
	}
}
