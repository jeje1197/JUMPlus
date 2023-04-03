package com.dollarsbank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dollarsbank.exception.InvalidAccountIdException;
import com.dollarsbank.exception.InvalidCredentialsException;
import com.dollarsbank.exception.InvalidOptionException;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.BalancePrinter;
import com.dollarsbank.utility.ConsolePrinter;
import com.dollarsbank.utility.CustomerPrinter;
import com.dollarsbank.utility.OptionSelector;
import com.dollarsbank.utility.PrettyFormatter;
import com.dollarsbank.utility.TransactionPrinter;

public class DollarsBankController {
	private List<Account> accounts = new ArrayList<>();
	private Account currentAccount;

	public void createNewAccount() {
		ConsolePrinter.print(ConsolePrinter.ANSI_BLUE, PrettyFormatter.format("Enter Details For New Account"));

		try {
			// Get customer information
			ConsolePrinter.print("Customer Name:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			String customerName = OptionSelector.pickOption("[a-zA-Z]*(\s[a-zA-Z]*)*", "Please enter a valid name.");
			
			ConsolePrinter.print("Customer Address:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			String customerAddress = OptionSelector.pickOption("[0-9]+(\\s[a-zA-Z]*)*\\.?", "Please enter a valid address");
			
			ConsolePrinter.print("Customer Contact Number:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			String customerPhoneNumber = OptionSelector.pickOption("[0-9\\-]{10,13}", "Phone numbers must be 10 digits long"
					+ "and consist of only digits and optional dashes.");
			Customer customer = new Customer(customerName, customerAddress, customerPhoneNumber);
			
			// Get account information
			ConsolePrinter.print("User Id:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			String userId = OptionSelector.pickOption("[a-zA-Z_][a-zA-Z_0-9]{5,20}", "Username must be at least 6 characters long and cannot contain any spaces"
					+ " or special characters.");
			
			ConsolePrinter.print("Password:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			String password = OptionSelector.pickOption("[a-zA-Z_0-9!\\.]{8,20}", "Password must be at least 8 characters long and cannot"
					+ " contain any spaces or special characters.");
			
			ConsolePrinter.print("Initial Deposit:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			double initialDeposit = OptionSelector.pickOption(5.00, 999999.00, "Invalid initial deposit amount. Must be between $5.00"
					+ " and $999999.00");

			Account newAccount = new Account(userId, password, initialDeposit, customer);
			newAccount.addTransaction(String.format("Initial Deposit: $%.2f", initialDeposit));
			accounts.add(new Account(userId, password, initialDeposit, customer));
			ConsolePrinter.print("Account created!\n");
		} catch (InvalidOptionException e) {
			ConsolePrinter.print(ConsolePrinter.ANSI_RED, e.getMessage() + "\n");
		}
	}

	public boolean login() {
		try {
			ConsolePrinter.print(ConsolePrinter.ANSI_BLUE, PrettyFormatter.format("Enter Login Details"));
			ConsolePrinter.print("UserId:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			String userId = OptionSelector.pickOption(".*", "Invalid Credentials. Try again!");
			
			ConsolePrinter.print("Password:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			String password = OptionSelector.pickOption(".*", "Invalid Credentials. Try again!");
			
			Optional<Account> userAccount = accounts.stream().filter((account) -> {
				return userId.equals(account.getUserId()) && password.equals(account.getPassword());
			}).findFirst();
			
			if (userAccount.isEmpty()) {
				throw new InvalidCredentialsException("Invalid Credentials. Try again!");
			}
			
			currentAccount = userAccount.get();
			ConsolePrinter.print("Successfully logged in!\n");
		} catch (Exception e) {
			ConsolePrinter.print(ConsolePrinter.ANSI_RED, e.getMessage() + "\n");
			return false;
		}
		return true;
	}

	public void depositAmount() {
		BalancePrinter.print(currentAccount);

		try {
			ConsolePrinter.print("Deposit Amount:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			double depositAmount = OptionSelector.pickOption(5.00, 999999.00, "Invalid deposit amount. Must be between $5.00"
					+ " and $999999.00");
			
			currentAccount.setBalance(currentAccount.getBalance() + depositAmount);
			currentAccount.addTransaction(String.format("Deposited: $%.2f", depositAmount));
			BalancePrinter.print(currentAccount);
		} catch (Exception e) {
			ConsolePrinter.print(ConsolePrinter.ANSI_RED, e.getMessage() + "\n");
		}
		
		ConsolePrinter.print("");
	}

	public void withdrawAmount() {
		BalancePrinter.print(currentAccount);

		try {
			ConsolePrinter.print("Withdraw Amount:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			double withdrawAmount = OptionSelector.pickOption(0.01, currentAccount.getBalance(), 
					"Invalid withdraw amount. Must be between $0.01 and $" + currentAccount.getBalance());
			
			currentAccount.setBalance(currentAccount.getBalance() - withdrawAmount);
			currentAccount.addTransaction(String.format("Withdrew $%.2f", withdrawAmount));
			BalancePrinter.print(currentAccount);
		} catch (Exception e) {
			ConsolePrinter.print(ConsolePrinter.ANSI_RED, e.getMessage() + "\n");
		}

		ConsolePrinter.print("");
	}

	public void fundsTransfer() {
		BalancePrinter.print(currentAccount);

		try {
			// Look for account by userId
			ConsolePrinter.print(ConsolePrinter.ANSI_BLUE, PrettyFormatter.format("Enter the id of the account you "
					+ "want to transfer money to"));
			ConsolePrinter.print("Account Id:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			String accountId = OptionSelector.pickOption(".*", "Invalid account id. Try again!");
			
			// Get transfer amount
			ConsolePrinter.print("Amount to Transfer:");
			ConsolePrinter.setConsoleInputColor(ConsolePrinter.ANSI_GREEN);
			double transferAmount = OptionSelector.pickOption(0.01, currentAccount.getBalance(), 
					"Invalid transfer amount. Must be between $0.01 and $" + currentAccount.getBalance());
	
			// Find user account
			Optional<Account> userAccount = accounts.stream().filter((account) -> {
				return accountId.equals(account.getUserId());
			}).findFirst();

			if (userAccount.isEmpty()) {
				throw new InvalidAccountIdException("Could not find account id. Try again.");
			}

			currentAccount.setBalance(currentAccount.getBalance() - transferAmount);
			Account accountToTransferTo = userAccount.get();
			accountToTransferTo.setBalance(accountToTransferTo.getBalance() + transferAmount);

			// Add transaction to history
			currentAccount.addTransaction(String.format("Transfered $%.2f", transferAmount) + " to account [" + accountId + "]");
			accountToTransferTo.addTransaction(String.format("Received $%.2f", transferAmount) + " from account [" + currentAccount.getUserId() + "]");
			
			// Display balance
			BalancePrinter.print(currentAccount);
		} catch (Exception e) {
			ConsolePrinter.print(ConsolePrinter.ANSI_RED, e.getMessage() + "\n");
		}
	}

	public void viewRecentTransactions() {
		TransactionPrinter.print(currentAccount);
	}

	public void displayCustomerInformation() {
		CustomerPrinter.print(currentAccount.getCustomer());
	}
	
	public void logout() {
		currentAccount = null;
		ConsolePrinter.print("\nSuccessfully logged out.\n");
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
	
	public void setAccounts(List<Account> accounts) {
		if (accounts == null) {
			return;
		}
		this.accounts = accounts;
	}
}
