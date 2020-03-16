package bankstatement.exporter;

import bankstatement.domain.SummaryStatistics;

public class HtmlExporter implements Exporter {

    @Override
    public String export(SummaryStatistics summaryStatistics) {
        return "<!doctype html>\n" + "<html lang='en'>\n" +
                "<head><title>Bank Transaction Report</title></head>\n" +
                "<body>\n" +
                "   <ul>\n" +
                "       <li><strong>The sum is</strong>: " + summaryStatistics.getSum() + "</li>\n" +
                "       <li><strong>The average is</strong>: " + summaryStatistics.getAverage() + "</li>\n" +
                "       <li><strong>The max is</strong>: " + summaryStatistics.getMax() + "</li>\n" +
                "       <li><strong>The min is</strong>: " + summaryStatistics.getMin() + "</li>\n" +
                "   </ul>\n" +
                "</body>\n" +
                "</html>";
    }
}
