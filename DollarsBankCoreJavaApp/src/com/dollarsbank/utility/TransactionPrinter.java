package com.dollarsbank.utility;

import java.util.List;

import com.dollarsbank.model.Account;

public class TransactionPrinter {
	public static void print(Account account) {
		ConsolePrinter.print(ConsolePrinter.ANSI_BLUE, PrettyFormatter.format("5 Recent Transactions:"));
		List<String> transactionHistory = account.getTransactionHistory();
		int numberOfTransactions = transactionHistory.size();
		if (numberOfTransactions == 0) {
			ConsolePrinter.print("No transactions");
		} else {
			for (int i = 0; i < 5 && i < numberOfTransactions; i++) {
				ConsolePrinter.print(transactionHistory.get(numberOfTransactions - 1 - i));
			}
		}
		ConsolePrinter.print("");
	}
}
