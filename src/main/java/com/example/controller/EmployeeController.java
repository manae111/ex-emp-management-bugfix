package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Employee;
import com.example.form.InsertEmployeeForm;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@GetMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細画面を出力します.
	 * 
	 * @param id    リクエストパラメータで送られてくる従業員ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
	@GetMapping("/showDetail")
	public String showDetail(@RequestParam("id")String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を更新する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細(ここでは扶養人数のみ)を更新します.
	 * 
	 * @param form 従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@PostMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員をあいまい検索する
	/////////////////////////////////////////////////////
	/**
	 * 従業員をあいまい検索します.
	 * ・空文字で検索した場合→全件検索結果を表示させる
	 * ・指定した文字列が存在しなかった場合→「１件もありませんでした」というメッセージと共に全件検索結果を表示させる
	 * @param searchName
	 * @param model
	 * @return
	 */
	@PostMapping("/search")
	public String search(@RequestParam("searchName")String searchName, Model model) {
		if (searchName.equals("") || searchName == null) {
			return "redirect:/employee/showList";
		}
		List<Employee> employeeList = employeeService.search(searchName);
		if (employeeList.size() == 0) {
			employeeList = employeeService.showList();
			model.addAttribute("employeeList", employeeList);
			model.addAttribute("noEmployeeError", "１件もありませんでした");
		} else {
			model.addAttribute("employeeList", employeeList);
		}
		return "employee/list";
	}
	
	/////////////////////////////////////////////////////
	// ユースケース：従業員のあいまい検索のオートコンプリート機能のために
	// データベースから従業員の名前のリストをJSON形式で取得する
	/////////////////////////////////////////////////////
	@GetMapping("/names")
	@ResponseBody
	public ResponseEntity<List<String>> getEmployeeNames() {
    	List<Employee> employeeList = employeeService.showList();
    	List<String> employeeNames = employeeList.stream()
                                              	.map(Employee::getName)
                                              	.collect(Collectors.toList());
    	return ResponseEntity.ok(employeeNames);
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員登録画面を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員登録画面を出力します.
	 * 
	 */
	@GetMapping("/toRegister")
	public String toRegister() {
		System.out.println("！！！！！！！！！エラー500！！！！！！！！！！");
		return "employee/test";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員登録をする
	/////////////////////////////////////////////////////
	/**
	 * 従業員登録をします.
	 * 
	 */
	@PostMapping("/register")
	public String register(@Validated InsertEmployeeForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("！！！！！！！！！エラー！！！！！！！！！！");
			return toRegister();
		}
		Employee employee = new Employee();
		// フォームからドメインにプロパティ値をコピー
		BeanUtils.copyProperties(form, employee);
		//ダブルサブミット対策
		employeeService.register(employee);
		System.out.println("！！！！！！！！！エラー２！！！！！！！！！！");
		return "redirect:/employee/showList";
	}

}
