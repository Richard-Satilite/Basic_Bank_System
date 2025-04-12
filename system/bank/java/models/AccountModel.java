package models;

import java.util.ArrayList;
import java.util.Random;

public class AccountModel{

	private int accountCode;
	private double balance;
	private ArrayList<CustomerModel> customers;

	public AccountModel(){
		this.code = getRandomCode();
		this.balance = 0;
		this.customers = new ArrayList<>();
	}

	private int getRandomCode(){
		int rand;
		
		Random random = new Random(System.currentTimeMillis());
		
		rand = 1000 + random.nextInt(9000);
		random = null;
		
		return rand;
	}

	public int getAccountCode(){
		return this.accountCode;
	}

	public void addCustomer(CustomerModel customer){
		if(customer != null && !this.customers.contains(customer)){
			this.customers.add(customer);
			customer.addAccount(this);
		}
	}

	public void removeCustomer(CustomerModel customer){
		if(customer != null && this.customers.contains(customer)){
			this.customers.remove(customer);
			customer.removeAccount(this);
		}
	}

	public double getBalance(){
		return this.balance;
	}

	public double withdraw(double amount){
		
		if(amount >= 0 && this.balance >= amount){
			this.balance -= amount;
			return amount;
		} else{
			System.out.println("Incorrect amount or insufficient balance!");
		}

		return -1;
	}

	public void deposit(double amount){
		if(amount > 0){
			this.balance += amount
		} else{
			System.out.println("Incorrect deposit amount!");
		}
	}

	public void transferAmount(double amount, AccountModel accountToTransfer){
		
		if(accountTotransfer != null && withdraw(amount) != -1){
			accountToTransfer.deposit(amount);
			System.out.println("Successful deposit!");
		} else{
			System.out.println("Failed transfer! Check the amount value or if the account is correct.");
		}
	}

	public CustomerModel getCustomerByCPF(String CPF){
		for(CustomerModel ctm : this.customers){
			if(ctm.getCPF().equals(CPF)){
				return ctm;
			}
		}

		return null;
	}

	public String genStatement(String name){
		
		String content;
		Statement statement = new Statement();
		statement.genContent(this.balance, name);

		content = statement.getContent();

		return content;
	}
}
