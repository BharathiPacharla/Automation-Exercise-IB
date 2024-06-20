package glue;

import account.Account;
import account.Transaction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

public class AccountSteps {

    private Account account;

    @Given("^Account exists for Acc No\\. \"([^\"]*)\" with Name \"([^\"]*)\"$")
    public void accountExistsForAccNoWithName(String number, String name) {
        account = new Account(number, name);
    }

    @Given("^deposits are made$")
    public void depositsAreMade(List<Map<String, String>> deposits) {
        for (Map<String, String> deposit : deposits) {
            for (Map.Entry<String, String> entry : deposit.entrySet()) {
                String label = entry.getKey();
                String amountStr = entry.getValue();
                try {
                    double amount = Double.parseDouble(amountStr);
                    account.deposit(label, amount);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid amount format for deposit: " + amountStr);
                } catch (NullPointerException e) {
                    System.err.println("Null value encountered for deposit: " + amountStr);
                }
            }
        }
    }

    @Given("^withdrawls are made$")
    public void withdrawalsAreMade(List<Map<String, String>> withdrawals) {
        for (Map<String, String> withdrawal : withdrawals) {
            for (Map.Entry<String, String> entry : withdrawal.entrySet()) {
                String label = entry.getKey();
                String amountStr = entry.getValue();
                try {
                    double amount = Double.parseDouble(amountStr);
                    account.withdraw(label, amount);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid amount format for withdrawal: " + amountStr);
                } catch (NullPointerException e) {
                    System.err.println("Null value encountered for withdrawal: " + amountStr);
                }
            }
        }
    }

    @When("^statement is produced$")
    public void statementIsProduced() {
        account.produceStatement();
    }

    @Then("^statement includes \"([^\"]*)\"$")
    public void statementIncludes(String detail) {
        String statement = account.getStatement();
        if (!statement.contains(detail)) {
            throw new AssertionError("Statement does not include expected detail: " + detail);
        }
    }
}
