package src;

import models.*;
import java.util.Scanner;


public class Main{

	public void main(String[] args){
	
		BankModel bank = genBank("Unesp");
		Scanner stream = new Scanner(System.in);
		int input;

		do{
			startMessage(bank.getBankName());
			input = stream.nextInt();

			switch(input){
				case 1:
					int infos[];
					System.out.println("OPTION 1 - ACCESSING YOUR ACCOUNT\n");
						
					infos = accountAccessData(stream);					

					break;
				case 2:
					break;
				case 3:
					System.out.println("\nSee you later!");
					break;
				default:
					System.out.println("Invalid input! Type one of the options available!");
					break;
			}
		} while(input != 3);
	}


	private void startMessage(String bankName){
		System.out.println("""
			Hello, welcome to the %s Bank!

			what do you want to do today? Choose one of the options below!

			[1] - Access my account
			[2] - Create my account
			[3] - exit program
		""".format(bankName));
	}

	private int[] accountAccessData(Scanner stream){
		
		int[] infos = new int[2];

		do{
			System.out.print("\nType the agency code: ");
			
			if(stream.hasNextInt() && stream.nextInt() > 0){
				infos[0] = stream.nextInt();
			} else{
				System.out.println("Invalid agency code ... Please, put a corret value!");
			}
			
		}while(!stream.hasNextInt() || stream.nextInt() < 0);

		do{
			System.out.print("\nType the account code: ");
			
			if(stream.hasNextInt() && stream.nextInt() > 0){
				infos[1] = stream.nextInt();
			} else{
				System.out.println("Invalid account code ... Plase, put a correct value!");
			}

		}while(!stream.hasNextInt() || stream.nextInt() < 0);

		return infos;
	}

	private BankModel genBank(String name){
		return new BankModel(name);
	}

	private AccountModel accountAccess(BankModel bank,int agencyCode, int accountCode){
		AccountModel account = bank.getAccount(agencyCode, accountCode);

		return account;
	}

	private CustomerModel customerAccess(AccountModel account, String cpf){
		CustomerModel customer = account.getCustomerByCPF(cpf);

		return customer;
	}
}
