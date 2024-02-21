package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * ログイン時に使用するフォーム.
 * 
 * @author igamasayuki
 * 
 */
public class LoginForm {

	/** メールアドレス */
	/**入力値チェック　email形式・空欄*/
	@Email(message = "メールアドレスの形式が誤っています")
	@NotBlank(message = "メールアドレスを入力してください")
	private String mailAddress;
	/** パスワード */
	/**入力値チェック　空欄 */
	@NotBlank(message = "パスワードを入力してください")
	private String password;

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
		return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
	}

}
