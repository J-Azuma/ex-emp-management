package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
