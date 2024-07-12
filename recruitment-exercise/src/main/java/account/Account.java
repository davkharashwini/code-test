package account;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String name;
    private double balance;
    private List<String> transactions;

    public Account(String accountNumber, String name) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    /**
     * Deposit method
     * @param key
     * @param amount
     */
    public void deposit(String key,double amount) {
        balance += amount;
        transactions.add("Deposit: " + amount);
        transactions.add(key);
    }

    /**
     * Withdraw method
     * @param key
     * @param amount
     */
    public void withdraw(String key,double amount) {
        balance -= amount;
        transactions.add("Withdrawal: " + amount);
        transactions.add(key);
    }

    /**
     * Generate statement method
     * @return
     */
    public String generateStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("Name: ").append(name).append("\n");
        statement.append("Account: ").append(accountNumber).append("\n");
        for (String transaction : transactions) {
            statement.append(transaction).append("\n");
        }
        statement.append("Balance: ").append(balance).append("\n");
        return statement.toString();

    }
}
