package bankstatement.exporter;

import bankstatement.domain.SummaryStatistics;

public interface Exporter {
    String export(SummaryStatistics summaryStatistics);
}
