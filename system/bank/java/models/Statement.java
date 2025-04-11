package models;

import utils.Printer;

public class Statement{
	private String content;


	public String getContent(){
		return this.content;
	}


	public void genContent(double balance, String customerName){
		this.content = """
			***************************
				Statement
			***************************

			Customer: %s
			Balance: R$ %.2f
			

			---------------------------
		""".format(customerName, balance);
	}

	public void exportToPDF(){
		if(this.content != null){
			System.out.println("The bank statement content was converted to PDF!");
		} else{
			System.out.println("Conversion Failled:\nbank statement content is undefined!");
		}
	}

	public void printStatement(){
		Printer.printContent(this.content);
	}
}
