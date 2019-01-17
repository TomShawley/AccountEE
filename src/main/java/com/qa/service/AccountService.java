package com.qa.service;

public interface AccountService {
	String getAllAccounts();
	String createAccount(String accout);
	String deleteAccount(Long id);
	String findAnAccount(Long id);
	String updateAnAccount(Long id);

}
