package models;

import java.util.ArrayList;

public class CustomerModel{

	private String name;
	private ArrayList<AccountModel> accounts;

	public CustomerModel(String name){
		this.name = name;
		this.accounts = new ArrayList<>();
	}

	public void printStatement(int accountCode){
		Account account = null;
		
		for(Account acc : accounts){
			if(acc.getAccountCode() == accountCode){
				account = acc;
				break;
			}
		}
		
		if(account){
			
		}
	}

	public boolean addAccount(AccountModel account){
		if(account && !this.accounts.contains(account)){
			this.accounts.add(account);
			account.addCustomer(this);
		}
	}

	public void removeAccount(AccountModel account){
		if(account && this.accounts.contains(account)){
			this.accounts.remove(account);
			account.removeCustomer(this);
		}
	}

}
