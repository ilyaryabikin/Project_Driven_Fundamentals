package bankstatement.function.filter;

import bankstatement.domain.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction transaction);
}
