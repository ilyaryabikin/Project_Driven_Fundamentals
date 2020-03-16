import bankstatement.domain.BankTransaction;
import org.junit.Assert;
import org.junit.Test;
import bankstatement.parser.BankStatementCSVParser;
import bankstatement.parser.BankStatementParser;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementCSVParserTest {
    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        final String line = "30-01-2017,-50,Tesco";
        final BankTransaction result = statementParser.parseFrom(line);
        final BankTransaction expected = new BankTransaction(
                LocalDate.of(2017, Month.JANUARY, 30),
                -50,
                "Tesco");
        final double tolerance = 0.0d;

        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseManyCorrectLines() throws Exception {
        final List<String> lines = List.of(
                "30-01-2017,-50,Tesco",
                "02-02-2017,2000,Royalties",
                "02-02-2017,-4000,Rent");
        final List<BankTransaction> result = statementParser.parseLinesFrom(lines);
        final List<BankTransaction> expected = List.of(
                new BankTransaction(
                        LocalDate.of(2017, Month.JANUARY, 30),
                        -50,
                        "Tesco"),
                new BankTransaction(
                        LocalDate.of(2017, Month.FEBRUARY, 2),
                        2000,
                        "Royalties"),
                new BankTransaction(
                        LocalDate.of(2017, Month.FEBRUARY, 2),
                        -4000,
                        "Rent"));
        Assert.assertEquals(expected, result);
    }
}
