package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

/**
 * @author junpei.azuma
 * 管理者登録画面を表示する処理を書く
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	/**
	 * この変数は後で使う
	 */
	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * @return
	 * フォームオブジェクトを生成する
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * @return
	 * 管理者登録画面を表示する
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}
	
	/**
	 * @param form
	 * @return String
	 * 管理者情報を登録する
	 * 現段階ではリダイレクト後に404が出ます
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator= new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
		
	}
} 
