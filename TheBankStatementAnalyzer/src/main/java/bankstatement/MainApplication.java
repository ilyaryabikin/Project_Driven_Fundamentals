package bankstatement;

import bankstatement.analyzer.BankStatementAnalyzer;
import bankstatement.exporter.Exporter;
import bankstatement.exporter.HtmlExporter;
import bankstatement.parser.BankStatementCSVParser;
import bankstatement.parser.BankStatementParser;

import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) throws IOException {
        BankStatementAnalyzer analyzer = new BankStatementAnalyzer();
        BankStatementParser parser = new BankStatementCSVParser();
        Exporter exporter = new HtmlExporter();
        System.out.println(analyzer.analyze(args[0], parser, exporter));
    }
}
