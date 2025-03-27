/**
 * Manages a bank account with functionalities such as
 * depositing, withdrawing, and balance enquiry
 * @author Amaury Perraud
 * @author Lilian LABAT
 */
public class BankAccount
{
    private static final int MIN_ACCOUNT_BALANCE = 0;
    private static final int MAX_ACCOUNT_BALANCE;
    private static final int MIN_DEPOSIT_AMOUNT = 0;
    private static final int MIN_WITHDRAW_BALANCE = 0;

    private final String name;

    private int balanceUsd;

    static
    {
        MAX_ACCOUNT_BALANCE = Integer.MAX_VALUE;
    }

    /**
     * Create a new bank account using the specified arguments.
     * @param name The name of the account
     * @param balance The starting balance of the account
     */
    public BankAccount(final String name, final int balance)
    {
        validateAccountName(name);
        validateAccountBalance(balance);

        this.name = name;
        this.balanceUsd = balance;
    }

    private static void validateAccountName(final String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be blank or null");
        }
    }

    private static void validateAccountBalance(final int balance)
    {
        if (balance < MIN_ACCOUNT_BALANCE || balance > MAX_ACCOUNT_BALANCE)
        {
            throw new IllegalArgumentException("Invalid balance");
        }
    }

    /**
     * Deposit specified amount to the account. If the amount is invalid or final balance exceeds
     * @param amountUsd The amount to deposit
     */
    public void deposit(final int amountUsd)
    {
        validateDeposit(amountUsd);

        balanceUsd += amountUsd;
    }

    private void validateDeposit(final int amountUsd)
    {
        if (amountUsd <= MIN_DEPOSIT_AMOUNT)
        {
            throw new IllegalArgumentException("Invalid deposit amountUsd");
        }

        if (MAX_ACCOUNT_BALANCE - amountUsd < balanceUsd)
        {
            throw new IllegalArgumentException("Max account balance exceeded");
        }
    }

    /**
     * Withdraw specified amount to the account. If the amount is invalid or final balance exceeds
     * @param amountUsd The amount to withdraw
     */
    public void withdraw(final int amountUsd)
    {
        validateWithdraw(amountUsd);

        balanceUsd -= amountUsd;
    }

    private void validateWithdraw(final int amountUsd)
    {
        if (amountUsd <= MIN_WITHDRAW_BALANCE)
        {
            throw new IllegalArgumentException("Invalid deposit amountUsd");
        }

        if (MIN_WITHDRAW_BALANCE + amountUsd > balanceUsd)
        {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    /**
     * Transfer the specified amount from this to specified account.
     * @param account The account to transfer the money to
     * @param accountName This account name
     * @param amountUsd The amount to transfer
     */
    public void transferToBank(final BankAccount account, final String accountName, final int amountUsd)
    {
        validateBankTransfer(account, accountName, amountUsd);

        this.withdraw(amountUsd);
        account.deposit(amountUsd);
    }

    private void validateBankTransfer(final BankAccount account, final String accountName, final int amountUsd)
    {
        if (!accountName.equals(this.name))
        {
            throw new IllegalArgumentException("Account name does not match");
        }

        this.validateWithdraw(amountUsd);
        account.validateDeposit(amountUsd);
    }

    /**
     * Return the balance of the account in USD.
     * @return The balance USD
     */
    public int getBalanceUsd()
    {
        return balanceUsd;
    }

    /**
     * Return the name of the account.
     * @return The name of the account
     */
    public String getAccountId()
    {
        return name;
    }
}
