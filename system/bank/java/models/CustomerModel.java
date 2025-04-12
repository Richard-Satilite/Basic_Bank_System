package models;

import java.util.ArrayList;
import utils.*;

public class CustomerModel{

	private String name;
	private String cpf;
	private ArrayList<AccountModel> accounts;

	public CustomerModel(String name, String cpf){
		if(name != null && !name.isEmpty() && ValidCPF.isValid(cpf)){
			this.name = name;
			this.cpf = cpf.replaceAll("[^0-9], "");
			this.accounts = new ArrayList<>();
		} else{
			System.out.pritln("Customer informations is invalid!");
		}
	}

	public void printStatement(int accountCode){
		Account account = null;
		
		for(Account acc : accounts){
			if(acc.getAccountCode() == accountCode){
				account = acc;
				break;
			}
		}
		
		if(account != null){
			Printer.printContent(account.genStatement(this.name));
		} else{
			System.out.println("There's no account with code: " + accountCode);
		}
	}

	public void addAccount(AccountModel account){
		if(account != null && !this.accounts.contains(account)){
			this.accounts.add(account);
			account.addCustomer(this);
		}
	}

	public void removeAccount(AccountModel account){
		if(account != null && this.accounts.contains(account)){
			this.accounts.remove(account);
			account.removeCustomer(this);
		}
	}


	public String getName(){
		return this.name;
	}

	public String getCPF(){
		return this.CPF;
	}
}
