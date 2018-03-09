package com.jcg.examples.viewBean;

import java.util.ArrayList;

public class UserDetailsBean {
	
	private String accountBal;	
	private ArrayList<Transactions> transactionDetails;
	public String getAccountBal() {
		return accountBal;
	}
	public void setAccountBal(String accountBal) {
		this.accountBal = accountBal;
	}
	public ArrayList<Transactions> getTransactionDetails() {
		return transactionDetails;
	}
	public void setTransactionDetails(ArrayList<Transactions> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	
	

}
