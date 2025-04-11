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
		if(customer && !this.customers.contains(customer)){
			this.customers.add(customer);
			customer.addAccount(this);
		}
	}

	public void removeCustomer(CustomerModel customer){
		if(customer && this.customers.contains(customer)){
			this.customers.remove(customer);
			customer.removeAccount(this);
		}
	}
}
