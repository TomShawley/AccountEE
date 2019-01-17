package com.qa.service;

public interface AccountService {

	String getAllAccounts();

	String addAccount(String account);

	String deleteAccount(Long id);

}