package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * 管理者情報を登録します.
	 * パスワードはハッシュ化されます.
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		String hashedPassword = bCryptPasswordEncoder.encode(administrator.getPassword());
		administrator.setPassword(hashedPassword);
		administratorRepository.insert(administrator);
	}

	/**
	 * ログインをします.
	 * パスワードはハッシュ化されます.
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 管理者情報 存在しない場合はnullが返ります
	 */
	public Administrator login(String mailAddress, String password) {
		Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
		if (administrator == null) {
			return null;
		} else if (bCryptPasswordEncoder.matches(password, administrator.getPassword())) {
			return administrator;
		} else {
			return null;
		}
	}

	/**
	 * メールアドレスから管理者情報を取得します.
	 * 
	 * @param mailAddress メールアドレス
	 * @return 管理者情報 存在しない場合はnullが返ります
	 */
	public Administrator DuplicationMailaddress(String mailAddress) {
		return administratorRepository.findByMailAddress(mailAddress);
	}
}
