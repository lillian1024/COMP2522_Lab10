import java.util.HashMap;
import java.util.Map;

/**
 * Manages a collection of BankAccount objects, allowing the addition of
 * new accounts and management through unique account IDs
 *
 * @author Amaury Perraud
 * @author Lilian LABAT
 *
 * @version 1.0
 */
public class Bank {
    private final Map<String, BankAccount> accounts;

    Bank()
    {
        this.accounts = new HashMap<>();
    }


    /**
     * check if account doesn't already exist
     * @param account new account to be added
     */
    public void addAccount(BankAccount account) {
        if (accounts.containsKey(account.getAccountId())) {
            throw new IllegalArgumentException("Account ID already exists");
        }
        accounts.put(account.getAccountId(), account);
    }

    /**
     * @param accountId unique id of each account
     * @return the account if found else an illegalArgumentException
     */
    public BankAccount retrieveAccount(String accountId) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        return accounts.get(accountId);
    }

    /**
     * @return the sum of all accounts balance
     */
    public int totalBalanceUsd() {
        return accounts.values().stream().mapToInt(BankAccount::getBalanceUsd).sum();
    }
}

