package engine.utility;

import engine.domain.Action;
import engine.domain.Condition;
import engine.domain.Rule;

public class RuleBuilder {
    private Condition condition;

    private RuleBuilder(Condition condition) {
        this.condition = condition;
    }

    public static RuleBuilder when(Condition condition) {
        return new RuleBuilder(condition);
    }

    public Rule then(Action action) {
        return new DefaultRule(condition, action);
    }
}
