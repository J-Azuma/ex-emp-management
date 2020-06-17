package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者情報の操作を行うコントローラ.
 * 
 * @author junpei.azuma
 * 
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
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 管理者登録画面を表示する.
	 * 
	 * @return 管理者登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}
	
	/** 管理者情報を登録する.
	 * 現段階ではリダイレクト後に404が出ます.
	 * 
	 * @param form 管理者登録フォームのオブジェクト
	 * @return String 管理者登録画面のパス
	 * 
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator= new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
		
	}
	
	/** 
	 * ログイン画面を表示する.
	 * 
	 * @return ログイン画面
	 * 
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	/** 
	 * ログイン処理を行う.
	 * 
	 * @param form ログイン用フォーム
	 * @param model リクエストスコープにデータを渡すためのオブジェクト
	 * @return 成功時：従業員一覧 失敗時: ログイン画面に戻る
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator admin = administratorService.login(form.getMailAddress(), form.getPassword());
		if (admin == null) {
			String errorMessage = "メールアドレスまたはパスワードが不正です。";
			model.addAttribute("errorMessage", errorMessage);
			return toLogin();
		} else {
			String administratorName = admin.getName();
			session.setAttribute("administratorName", administratorName);
			return "forward:/employee/showList";
		}
		
	}
} 
