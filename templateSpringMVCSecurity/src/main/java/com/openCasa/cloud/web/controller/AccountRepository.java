package com.openCasa.cloud.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.openCasa.cloud.web.model.Account;

@Repository
@Transactional(readOnly = true)
public class AccountRepository {
	/*
	 * @PersistenceContext private EntityManager entityManager;
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	private Map<String, Account> userMap;

	@PostConstruct
	protected void initialize() {
		this.userMap = new HashMap<String, Account>();
	}

	@Transactional
	public Account save(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		// entityManager.persist(account);
		this.userMap.put(account.getEmail(), account);
		return account;
	}

	public Account findByEmail(String email) {
		/*
		 * try { return entityManager.createNamedQuery(Account.FIND_BY_EMAIL,
		 * Account.class) .setParameter("email", email) .getSingleResult(); }
		 * catch (PersistenceException e) { return null; }
		 */
		return this.userMap.get(email);
	}

}
