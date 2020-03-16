package bankstatement.analyzer;

import bankstatement.domain.BankTransaction;
import bankstatement.domain.SummaryStatistics;
import bankstatement.exporter.Exporter;
import bankstatement.parser.BankStatementParser;
import bankstatement.processor.BankStatementProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public String analyze(String fileName, BankStatementParser parser, Exporter exporter) throws IOException {
        Path path = Paths.get(RESOURCES + fileName);
        List<String> lines = Files.readAllLines(path);
        List<BankTransaction> transactions = parser.parseLinesFrom(lines);
        BankStatementProcessor processor = new BankStatementProcessor(transactions);
        SummaryStatistics summaryStatistics = processor.summarizeTransactions();
        return exporter.export(summaryStatistics);
    }
}
