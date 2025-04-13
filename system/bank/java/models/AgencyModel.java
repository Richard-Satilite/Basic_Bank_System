package models;

import java.util.ArrayList;

public class AgencyModel{

	private int agencyCode;
	private ArrayList<AccountModel> accounts;

	public AgencyModel(int code){
		this.code = code;
		this.accounts = new ArrayList<>();
	}


	public int getAgencyCode(){
		return this.agencyCode;
	}

	public boolean addAccount(AccountModel account){
		
		if(account != null && !this.accounts.contains(account)){
			this.accounts.add(account);
			return true;
		} else{
			System.out.println("Invalid account or the account already added");
			return false;
		}
	}

	public boolean removeAccount(int accountCode){
		if(accountCode > 999 && accountCode < 10000){
			if(accounts.removeIf(acc -> acc.getAccountCode() == accountCode)){
				return true;	
			} else{
				System.out.println("There's no account with code " + accountCode + "!");
			}
		} else{
			System.out.println("Invalid account code!");
		}

		return false;
	}

	public AccountModel getAccountByCode(int accountCode){
		for(AccountModel acc : this.accounts){
			if(acc.getAccountCode() == accountCode){
				return acc;
			}
		}

		return null;
	}

	public boolean transferAccount(int accountCode, AgencyModel newAgency){

		AccountModel account = getAccountByCode(accountCode);

		if(account != null){
			if(newAgency.addAccount(account)){
				this.accounts.remove(account);
				return true;
			}
		} else{
			System.out.println("The account with code " + accountCode + " doesn't exist in this agency!");
		}

		return false;
	}
}
