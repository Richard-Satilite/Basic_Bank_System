package src;

import models.*;
import java.util.Scanner;


public class Main{

	public static void main(String[] args){
	
		BankModel bank = new BankModel("Unesp");
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


	private static void startMessage(String bankName){
		System.out.println(String.format(
			"""
			Hello, welcome to the %s Bank!

			what do you want to do today? Choose one of the options below!

			[1] - Access my account
			[2] - Create my account
			[3] - exit program
		""", bankName));
	}

	private static int[] accountAccessData(Scanner stream){
		
		int[] infos = new int[2];
		int agencyCode;
		int accountCode;
		int valid = 0;

		while (valid == 0){

			System.out.print("\nType the agency code: ");

			if(stream.hasNextInt()){
				agencyCode = stream.nextInt();

				if(agencyCode > 0){
					valid = 1;
					infos[0] = agencyCode;
				}
			}
			
			if(valid == 0){
				System.out.println("\nInvalid agency code ... Please, put a corret value!");
				stream.next();
			}
		}

		valid = 0;

		while(valid == 0){
			System.out.print("\nType the account code: ");
		
			if(stream.hasNextInt()){
				accountCode = stream.nextInt();
		
				if(accountCode > 0){
					valid = 1;
					infos[1] = accountCode;
				}
			}

			if(valid == 0){
				System.out.println("\nInvalid account code ... Please, put a corret value!");		
				stream.next();
			}
		}

		return infos;
	}

	private static AccountModel accountAccess(BankModel bank,int agencyCode, int accountCode){
		AccountModel account = bank.getAccount(agencyCode, accountCode);

		return account;
	}

	private static CustomerModel customerAccess(AccountModel account, String cpf){
		CustomerModel customer = account.getCustomerByCPF(cpf);

		return customer;
	}
}
