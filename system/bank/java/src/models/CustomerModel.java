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
			this.cpf = cpf.replaceAll("[^0-9]", "");
			this.accounts = new ArrayList<>();
		} else{
			System.out.println("Customer informations is invalid!");
		}
	}

	public void printStatement(Statement statement){
		
		if(statement != null){
			Printer.printContent(statement.getContent());
		} else{
			System.out.println("There's no statement");
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
		return this.cpf;
	}

	public void setCPF(String cpf){
		if(ValidCPF.isValid(cpf)){
			this.cpf = cpf.replaceAll("[^0-9]", "");
		}
	}
}
