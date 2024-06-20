package account;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String accountNumber;
    private String accountName;
    private List<Transaction> transactions;
    private double balance;

    public Account(String accountNumber, String accountName) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.transactions = new ArrayList<>();
        this.balance = 0.0;
    }

    public void deposit(String label, double amount) {
        transactions.add(new Transaction(label, amount));
        balance += amount;
    }

    public void withdraw(String label, double amount) {
        transactions.add(new Transaction(label, -amount));
        balance -= amount;
    }

    public void produceStatement() {

    }

    public String getStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("Name: ").append(accountName).append("\n");
        statement.append("Account: ").append(accountNumber).append("\n");
        statement.append("Balance: ").append(balance).append("\n");
        statement.append("Transactions:").append("\n");
        for (Transaction transaction : transactions) {
            statement.append(transaction.toString()).append("\n");
        }
        return statement.toString();
    }
}
