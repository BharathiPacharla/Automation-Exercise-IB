package account;

public class Transaction {

    private String label;
    private double amount;

    public Transaction(String label, double amount) {
        this.label = label;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return label + ": " + amount;
    }
}
