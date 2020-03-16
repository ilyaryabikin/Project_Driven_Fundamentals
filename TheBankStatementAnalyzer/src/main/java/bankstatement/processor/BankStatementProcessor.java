package bankstatement.processor;

import bankstatement.domain.BankTransaction;
import bankstatement.domain.SummaryStatistics;
import bankstatement.function.filter.BankTransactionFilter;
import bankstatement.function.summarizer.BankTransactionSummarizer;

import java.time.Month;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public SummaryStatistics summarizeTransactions() {
        DoubleSummaryStatistics doubleSummaryStatistics = bankTransactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .summaryStatistics();
        return new SummaryStatistics(doubleSummaryStatistics.getSum(),
                doubleSummaryStatistics.getMax(),
                doubleSummaryStatistics.getMin(),
                doubleSummaryStatistics.getAverage());
    }

    public double summarizeTransactions(BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for (BankTransaction transaction: bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, transaction);
        }
        return result;
    }

    public double calculateTotalInMonth(Month month) {
        return summarizeTransactions((accumulator, transaction) ->
                transaction.getDate().getMonth().equals(month) ?
                accumulator + transaction.getAmount() :
                accumulator);
    }

    public List<BankTransaction> findTransactions(BankTransactionFilter bankTransactionFilter) {
        List<BankTransaction> result = new ArrayList<>();
        for (BankTransaction transaction: bankTransactions) {
            if (bankTransactionFilter.test(transaction)) {
                result.add(transaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual (int amount) {
        return findTransactions(transaction -> transaction.getAmount() >= amount);
    }
}
