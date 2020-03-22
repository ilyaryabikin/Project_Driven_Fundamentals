package engine;

import engine.domain.Facts;
import engine.domain.Rule;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleEngine {
    private final List<Rule> rules;
    private final Facts facts;

    public BusinessRuleEngine(Facts facts) {
        this.facts = facts;
        this.rules = new ArrayList<>();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public int count() {
        return rules.size();
    }

    public void run() {
        rules.forEach(rule -> rule.perform(facts));
    }
}
