package com.board.recipes.controllers;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class RecipesController {

	@Autowired
	RecipesService service;

	private static final Logger log = Logger.getLogger(RecipesController.class);

	@GetMapping("/index")
	public String index(Model model) {
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
		List<Map<String, Object>> list = service.detailviewRecipe(articleId);
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

	@GetMapping("recipe/add")
	public String getAdd(@RequestParam(name="id", defaultValue = "") Integer articleId,
			RecipesForm form, Model model) {
			List<Map<String, Object>> list = service.detailviewRecipe(articleId);
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
