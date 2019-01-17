package com.qa.service;

import javax.inject.Inject;

import com.qa.repository.AccountInterface;

public class AccountServiceImpl implements AccountService {
	@Inject
	private AccountInterface repo;

	@Override
	public String getAllAccounts() {
		
		return repo.getAllAccounts();
	}

	@Override
	public String createAccount(String accout) {
		
		return repo.createAccount(accout);
	}

	@Override
	public String deleteAccount(Long id) {
		
		return repo.deleteAccount(id);
	}

	@Override
	public String findAnAccount(Long id) {
		
		return repo.findAnAccount(id);
	}

	@Override
	public String updateAnAccount(Long id) {
		
		return repo.updateAnAccount(id);
	}

}
