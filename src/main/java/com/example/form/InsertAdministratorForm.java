package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 管理者情報登録時に使用するフォーム.
 * 
 * @author igamasayuki
 * 
 */
public class InsertAdministratorForm {
	/** 名前 */
	/**入力値チェック　空欄 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/** メールアドレス */
	/**入力値チェック　email形式・空欄*/
	@Email(message = "メールアドレスの形式が誤っています")
	@NotBlank(message = "メールアドレスを入力してください")
	private String mailAddress;
	/** パスワード */
	/**入力値チェック　空欄 */
	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 8,message = "パスワードは8文字以上で入力してください")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

}
