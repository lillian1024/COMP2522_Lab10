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

    public int getBalanceUsd()
    {
        return balanceUsd;
    }

    public String getAccountId()
    {
        return name;
    }
}
