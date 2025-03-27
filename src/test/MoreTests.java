/**
 * @author Amaury Perraud
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class that implements more tests for the Bank and BankAccount classes.
 * @author Amaury Perraud
 * @author Lilian LABAT
 */
public class MoreTests {
    private Bank bank;
    private BankAccount account1;
    private BankAccount account2;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        account1 = new BankAccount("11111", 500);
        account2 = new BankAccount("22222", 1000);
        bank.addAccount(account1);
        bank.addAccount(account2);
    }

    @Test
    void initialBalanceIsCorrect() {
        assertEquals(500, account1.getBalanceUsd());
        assertEquals(1000, account2.getBalanceUsd());
    }

    @Test
    void cannotDepositNegativeOrZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> account1.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account1.deposit(-50));
    }

    @Test
    void cannotWithdrawNegativeOrZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> account1.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account1.withdraw(-100));
    }

    @Test
    void cannotAddDuplicateAccount() {
        BankAccount duplicateAccount = new BankAccount("11111", 300);
        assertThrows(IllegalArgumentException.class, () -> bank.addAccount(duplicateAccount));
    }

    @Test
    void sumBalancesFromMultipleAccounts() {
        assertEquals(1500, bank.totalBalanceUsd());
        bank.addAccount(new BankAccount("33333", 200));
        assertEquals(1700, bank.totalBalanceUsd());
    }
}