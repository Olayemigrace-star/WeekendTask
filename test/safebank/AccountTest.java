package safebank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import safebank.exceptions.InsufficientFundsException;
import safebank.exceptions.InvalidAmountException;
import safebank.exceptions.WithdrawalLimitException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private Account account;

    @BeforeEach
    void setUp(){
        account = new Account("Eze Adora", 200000.0);
    }

    @Test
    void testSuccessfulldeposit(){
        account.deposit(20000);
        assertEquals(220000.0, account.getBalance());
    }

    @Test
    void testNegativeDepositThrowsExceptionAndPreservesBalance() {
        assertThrows(InvalidAmountException.class, () -> account.deposit(-500.0));
        assertEquals(200000.0, account.getBalance(), "Balance must remain unchanged after failed deposit");
    }

    @Test
    void testWithdrawalExceedingLimitThrowsException() {
        assertThrows(WithdrawalLimitException.class, () -> account.withdraw(120000.0));
        assertEquals(200000.0, account.getBalance());
    }


    @Test
    void testTransactionHistoryRecordsEvents() {
        assertEquals(1, account.getTransactionHistory().size());

        account.deposit(50000.0);

        List<String> history = account.getTransactionHistory();
        assertEquals(2, history.size());
        assertTrue(history.get(1).contains("Deposited: 50000.00"));
    }

    @Test
    void testInsufficientFundsThrowsException() {
        Account poorAccount = new Account("John", 5000.0);
        assertThrows(InsufficientFundsException.class, () -> poorAccount.withdraw(10000.0));
        assertEquals(5000.0, poorAccount.getBalance());
    }



}
