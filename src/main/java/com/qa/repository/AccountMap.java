package com.qa.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
@Transactional(SUPPORTS)
public class AccountMap implements AccountInterface {
	@Inject
	private JSONUtil util;
	@Inject
	private Account account;

	private Map<Integer, Account> accounts = new HashMap<>();;

	private int count = 0;

	@Override
	public String getAllAccounts() {
		return util.getJSONForObject(accounts);
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String accout) {

		accounts.put(count, util.getObjectForJSON(accout, Account.class));
		count++;

		return "Account created";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		boolean countExists = accounts.containsKey(id);
		if (countExists) {
			accounts.remove(id);
		}
		return "Account deleted";
	}

	@Override
	public String findAnAccount(Long id) {
		Account person = accounts.get(id);
		return util.getJSONForObject(person);

	}

	@Override
	@Transactional(REQUIRED)
	public String updateAnAccount(Long id) {
		Account person = accounts.get(id);
		person.setFirstName("Tom");
		return "account updated";
	}

}


