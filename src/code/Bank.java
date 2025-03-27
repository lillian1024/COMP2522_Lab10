import java.util.HashMap;
import java.util.Map;

/**
 * Manages a collection of BankAccount objects, allowing the addition of
 * new accounts and management through unique account IDs
 * @author Amaury Perraud
 * @author Lilian LABAT
 */
class Bank {
    private final Map<String, BankAccount> accounts;

    Bank()
    {
        this.accounts = new HashMap<>();
    }


    public void addAccount(BankAccount account) {
        if (accounts.containsKey(account.getAccountId())) {
            throw new IllegalArgumentException("Account ID already exists");
        }
        accounts.put(account.getAccountId(), account);
    }

    public BankAccount retrieveAccount(String accountId) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        return accounts.get(accountId);
    }

    public int totalBalanceUsd() {
        return accounts.values().stream().mapToInt(BankAccount::getBalanceUsd).sum();
    }
}

