package safebank.exceptions;

public class WithdrawalLimitException extends RuntimeException {
    public WithdrawalLimitException(String message) {
        super(message);
    }
}
