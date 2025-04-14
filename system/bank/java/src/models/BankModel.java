package models;

import java.util.ArrayList;

public class BankModel{
	
	private String bankName;
	private ArrayList<AgencyModel> agencies;

	public BankModel(String bankName){

		if(bankName != null && !bankName.isEmpty()){
			this.bankName = bankName;
			agencies = new ArrayList<>();

			for(int i = 1; i < 6; i++){
				agencies.add(new AgencyModel(i));
			}
		} else{
			System.out.println("Error while generating bank:\nInvalid bank name!");
		}
	}

	public boolean genAgency(int agencyCode){
		
		for (AgencyModel agy : this.agencies){
			if(agy.getAgencyCode() == agencyCode){
				return false;
			}
		}
		
		AgencyModel agency = new AgencyModel(agencyCode);

		this.agencies.add(agency);
		return true;
	}

	public boolean removeAgency(int agencyCode){
		return this.agencies.removeIf(agy -> agy.getAgencyCode() == agencyCode);
	}

	private AgencyModel searchAgency(int agencyCode){
		for(AgencyModel agy : this.agencies){
			if(agy.getAgencyCode() == agencyCode){
				return agy;
			}
		}

		return null;
	}

	public AccountModel getAccount(int agencyCode, int accountCode){
		
		AgencyModel agency = searchAgency(agencyCode);		

		if(agency != null){
			return agency.getAccountByCode(accountCode);
		}

		return null;
	}

	public boolean addAccountInAgency(int agencyCode, AccountModel account){
		for(AgencyModel agy : this.agencies){
			if(agy.getAgencyCode() == agencyCode){
				return agy.addAccount(account);
			}
		}

		return false;
	}

	public ArrayList<Integer> listAgencies(){
		ArrayList<Integer> agencyCodes = new ArrayList<>();
		System.out.println("Agencies of the " + this.bankName + " bank:\n");

		System.out.println("|---------------------------------|");

		for(AgencyModel agy : this.agencies){
			System.out.println("Agency Code: " + agy.getAgencyCode() + "\n");
			agencyCodes.add(agy.getAgencyCode());
		}

		System.out.println("|---------------------------------|");

		return agencyCodes;
	}

	public String getBankName(){
		return this.bankName;
	}
}
