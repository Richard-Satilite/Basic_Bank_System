package utils;

public class ValidCPF{
	
	private static final int[] weightCPF = {11, 10, 9, 8 , 7, 6, 5, 4, 3, 2};

	public static boolean isValid(String CPF){

		String filteredCPF = CPF.replaceAll("[^0-9]", "");
		int sum = 0, q1, q2;

		if (filteredCPF == null || filteredCPF.length() != 11){
			return false;		
		}

		if(filteredCPF.matches("(\\d)\\1{10}")){
			return false;
		}		


		for(int i = 1; i < weightCPF.length; i++){
			sum += (filteredCPF.charAt(i - 1) - '0') * weightCPF[i];
		}

		q1 = sum % 11;

		if(q1 > 1 && filteredCPF.charAt(9) - '0' != 11 - q1){
			return false;
		} else if(q1 <= 1 && filteredCPF.charAt(9) != 0){
			return false;
		}

		sum = 0;

		for(int i = 0; i < weightCPF.length; i++){
			sum += (filteredCPF.charAt(i) - '0') * weightCPF[i];
		}

		q2 = sum % 11;

		if(q2 > 1 && filteredCPF.charAt(10) - '0' != 11 - q2){
			return false;
		} else if(q2 <= 1 && filteredCPF.charAt(10) != 0){
			return false;
		}

		return true;
	}

}
