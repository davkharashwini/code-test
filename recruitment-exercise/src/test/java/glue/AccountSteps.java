package glue;

import java.util.Map;

import account.Account;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AccountSteps {

    Account account;
    String statement;

    @Given("^Account exists for Acc No\\. \"([^\"]*)\" with Name \"([^\"]*)\"$")
    public void accountExistsForAccNoWithName(String accountNumber, String name) {
        account = new Account(accountNumber, name);
    }

    @Given("deposits are made")
    public void depositsAreMade(Map<String, Double> deposits) {
        for (Map.Entry<String, Double> deposit : deposits.entrySet()) {
            account.deposit(deposit.getKey(),deposit.getValue());
        }
    }

    @Given("withdrawls are made")
    public void withdrawlsAreMade(Map<String, Double> withdrawals) {
        for (Map.Entry<String, Double> withdrawal : withdrawals.entrySet()) {
            account.withdraw(withdrawal.getKey(),withdrawal.getValue());
        }
    }

    @When("statement is produced")
    public void statementIsProduced() {
        statement = account.generateStatement();
    }

    @Then("statement includes {string}")
    public void statementIncludes(String content) {
        Assert.assertTrue(statement.contains(content));
    }
}
