package bankstatement.function.summarizer;

import bankstatement.domain.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulator, BankTransaction transaction);
}
