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
    public void depositsAreMade(Map<String, Double> deposits) {
        for (Map.Entry<String, Double> entry : deposits.entrySet()) {
            account.deposit(entry.getKey(), entry.getValue());
        }
    }

    @Given("^withdrawls are made$")
    public void withdrawalsAreMade(Map<String, Double> withdrawals) {
        for (Map.Entry<String, Double> entry : withdrawals.entrySet()) {
            account.withdraw(entry.getKey(), entry.getValue());
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
