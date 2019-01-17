package com.qa.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDB implements AccountInterface {
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("SELECT a FROM Account a");
		Collection<Account> account = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(account);

	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String accout) {
		Account anAccount = util.getObjectForJSON(accout, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		Account accountInDB = manager.find(Account.class, id);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}

		return "{\"message\": \"account sucessfully deleted\"}";
	}

	@Override
	public String findAnAccount(Long id) {
		Account account = manager.find(Account.class, id);
		return util.getJSONForObject(account);
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAnAccount(Long id) {
		Account anAccount = manager.find(Account.class, id);
		manager.getTransaction().begin();
		anAccount.setFirstName("Tom");
		manager.getTransaction().commit();
		return "{\"message\": \"account has been sucessfully updated\"}";
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public JSONUtil getUtil() {
		return util;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}


