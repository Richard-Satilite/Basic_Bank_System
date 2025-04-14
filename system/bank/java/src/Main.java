package src;

import models.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main{

	public static void main(String[] args){
	
		BankModel bank = new BankModel("Unesp");
		Scanner stream = new Scanner(System.in);
		int input = -1;

		do{
			startMessage(bank.getBankName());

			if(stream.hasNextInt()){

				input = stream.nextInt();


				switch(input){
					case 1:
						int infos[];
						System.out.println("\nOPTION 1 - ACCESSING YOUR ACCOUNT\n\n");
						
						infos = accountAccessData(stream);

						AccountModel account = accountAccess(bank, infos[0], infos[1]);

						if(account != null){
							String cpf = "";
							System.out.print("\nGreat! This account are registered in this agency. Now, put your CPF (You can put it in any format): ");

							CustomerModel customer = null;
							

							while(customer == null && cpf != "q"){
								cpf = stream.next();

								customer = customerAccess(account, cpf);

								if(customer == null){
									System.out.println("\nThere's no account in this agency with the cpf " + cpf + ", verify if you put it correctly! Or put 'q' to back to the main menu.");
									stream.next();
								}
							}

							if(customer != null){
								dashBoard(bank, account, customer, stream);
							}
							
						} else{
							System.out.println("\nOops! Looks like the agency code or account code doesn't exist. If you don't have an account, choose the register option!\n\n");
						}
	
						break;
					case 2:
						accountRegister(bank, stream);
						break;
					case 3:
						System.out.println("\nSee you later!");
						break;
					default:
						System.out.println("Invalid option! Type one of the options available!");
						break;
				}
			} else{
				System.out.println("Invalid input! Type only one of the numbers that are available in the options.\n\n");
				stream.next();
			}
		} while(input != 3);

		stream.close();
	}

	private static void dashBoard(BankModel bank, AccountModel account, CustomerModel customer, Scanner stream){

		int input = -1;
		int valid = 0;
		double amount;

		do{

			System.out.println(String.format(
				"""


					Welcome back! %s

					Current Balance: R$ %.2f

					How can i help you today?

					[1] - Make a deposit
					[2] - Make a withdraw
					[3] - View my bank statement
					[4] - Transfer money
					[5] - Back to the menu
	
				""", customer.getName(), account.getBalance()));

			System.out.print("\nOption: ");
			
			if(stream.hasNextInt()){
				input = stream.nextInt();

				switch(input){
					case 1:
						valid = 0;
						
						do{
							System.out.print("\nType the amount that you want to deposit: ");
		
							if(stream.hasNextDouble()){
								amount = stream.nextDouble();

								if(amount >= 0){
									account.deposit(amount);
									valid = 1;
									System.out.println(String.format("\nThe value R$ %.2f was deposited in your account!\n", amount));
								}
							}

							if(valid == 0){
								System.out.println("\nInvalid amount!\n");
								stream.next();
							}

						}while(valid == 0);

						break;
					case 2:

						valid = 0;

						do{

							System.out.print("\nType the amount that you want to withdraw: ");

							if(stream.hasNextDouble()){

								amount = stream.nextDouble();

								if(account.withdraw(amount) != -1){
									valid = 1;
									System.out.println(String.format("\nThe value R$ %.2f was withdrawn of your account!\n", amount));
								}
							}

							if(valid == 0){
								System.out.println("\nInvalid amount!\n");
								stream.next();
							}

						}while(valid == 0);

						break;
					case 3:
						valid = 0;
						int statementInput = -1;
						Statement statement = account.genStatement(customer.getName());

						do{
							System.out.println("""
								Statement successful generated! What do you want to>

								[1] View statement
								[2] Print statement
								[3] Export statement to PDF
								[4] Back to my dashboard
							""");

							System.out.print("\n\nOption: ");
			  
							if(stream.hasNextInt()){
								statementInput = stream.nextInt();

								switch(statementInput){
									case 1:
										System.out.println(statement.getContent() + "\n");
										break;
									case 2:
										customer.printStatement(statement);
										System.out.println("\n");
										break;
									case 3:
										statement.exportToPDF();
										System.out.println("\n");
										break;
									case 4:	
										System.out.println("\nReturning ...\n");
										break;
									default:
										System.out.println("\nInvalid option! Choose one of the options listed\n");
										break;
								}
							} else{
								System.out.println("\nInvalid input!");
								stream.next();
							}
							
						}while(statementInput != 4);


						break;
					case 4:
						valid = 0;
						int[] accountInfos = new int[2];

						accountInfos = accountAccessData(stream);
						AccountModel accountToTransfer = accountAccess(bank, accountInfos[0], accountInfos[1]);

						if(accountToTransfer != null){
							do{
								System.out.print("\nType the value that you wants to transfer: ");
								if(stream.hasNextDouble()){
									amount = stream.nextDouble();
									if(account.transferAmount(amount, accountToTransfer)){
										valid = 1;
									}
								}

								if(valid == 0){
									System.out.println("\nInvalid amount! Type a correct value\n");
								}
							}while(valid == 0);
						}
						
						break;
					case 5:
						System.out.println("\nLogging out ...\n");
						break;
					default:
						System.out.println("Invalid option! Type one of the options listed!\n");
						break;
				}
			} else{
				System.out.println("\nInvalid option!");
				stream.next();
			}

		}while(input != 5);
			
	}

	private static void accountRegister(BankModel bank, Scanner stream){
		ArrayList<Integer> agencyCodes = bank.listAgencies();
		int agencyCode = -1;
		String cpf, name;

		do{

			System.out.print("\nType the agency code that you want to create an account: ");

			if(stream.hasNextInt()){
				agencyCode = stream.nextInt();
			}

			if(!agencyCodes.contains(agencyCode)){
				System.out.println("\nThe agency code typed doesn't exist in this bank. Choose one of the codes that appear in the list!");
			}

		}while(!agencyCodes.contains(agencyCode));

		System.out.print("\nNow type your name: ");
		name = stream.next();

		System.out.print("\nNow type you CPF (You can put it in any type format): ");
		cpf = stream.next();

		AccountModel account = new AccountModel();
		CustomerModel customer = new CustomerModel(name, cpf);

		while(customer.getCPF() == null){
			System.out.print("\nInvalid CPF, type a correct CPF value: ");
			cpf = stream.next();

			customer.setCPF(cpf);
		}

		account.addCustomer(customer);

		if(bank.addAccountInAgency(agencyCode, account)){
			System.out.println("\nAccount successful added!\n\nYour data access\n\nAgency code: " + agencyCode + "\nAccount code: " + account.getAccountCode() + "\n\nYou can login now :)\n");
		} else{
			System.out.println("\nAdd account in agency with code " + agencyCode + " was failed. Please, verify the informations and try again.\n");
		}
	}

	private static void startMessage(String bankName){
		System.out.println(String.format(
			"""
			Hello, welcome to the %s Bank!

			what do you want to do today? Choose one of the options below!

			[1] - Access my account
			[2] - Register a account
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
				stream.nextLine();
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
				stream.nextLine();
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
