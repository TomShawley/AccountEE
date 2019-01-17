package com.qa.repository;

public interface AccountInterface {

	String getAllAccounts();
	String createAccount(String accout);
	String deleteAccount(Long id);
	String findAnAccount(Long id);
	String updateAnAccount(Long id);

}

