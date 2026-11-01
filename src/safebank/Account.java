package safebank;

import safebank.exceptions.InsufficientFundsException;
import safebank.exceptions.InvalidAmountException;
import safebank.exceptions.WithdrawalLimitException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    private String holderName;
    private double balance;
    private final List<String> transactionHistory;
    private static final double MAX_WITHDRAWAL_LIMIT =100000.0;


    public Account(String holderName, double openingbalance) {
        this.holderName = holderName;
        this.balance = openingbalance;
        transactionHistory = new ArrayList<>();
        this.transactionHistory.add(String.format("Account created with opening balance: %.2f", openingbalance));

    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");

        }
        this.balance += amount;
        this.transactionHistory.add(String.format("Deposited: %.2f | New Balance: %.2f", amount, this.balance));
    }

    public void withdraw(double amount) {
        if (amount <= 0.0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }
        if (amount > MAX_WITHDRAWAL_LIMIT) {
            throw new WithdrawalLimitException("Withdrawal exceeds single-withdrawal limit of 100,000.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        this.balance -= amount;
        this.transactionHistory.add(String.format("Withdrew: %.2f | New Balance: %.2f", amount, this.balance));

    }
    public double getBalance() {
        return balance;
    }
    public String getHolderName() { return holderName; }
    public List<String> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }
}
