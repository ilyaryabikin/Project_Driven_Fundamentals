import engine.domain.Facts;
import engine.domain.Rule;
import engine.BusinessRuleEngine;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BusinessRuleEngineTest {

    @Test
    public void shouldHaveNoRulesInitially() {
        BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(new Facts());
        Assert.assertEquals(0, businessRuleEngine.count());
    }

    @Test
    public void shouldAddTwoRules() {
        BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(new Facts());
        Rule mockRule = Mockito.mock(Rule.class);
        businessRuleEngine.addRule(mockRule);
        businessRuleEngine.addRule(mockRule);
        Assert.assertEquals(2, businessRuleEngine.count());
    }

    @Test
    public void shouldPerformARuleWithFacts() {
        Facts mockFacts = Mockito.mock(Facts.class);
        BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);
        Rule mockRule = Mockito.mock(Rule.class);
        businessRuleEngine.addRule(mockRule);
        businessRuleEngine.run();

        Mockito.verify(mockRule).perform(mockFacts);
    }
}
