package engine.utility;

import engine.domain.Action;
import engine.domain.Condition;
import engine.domain.Facts;
import engine.domain.Rule;

public class DefaultRule implements Rule {
    private final Condition condition;
    private final Action action;

    public DefaultRule(Condition condition, Action action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public void perform(Facts facts) {
        if (condition.evaluate(facts)) {
            action.execute(facts);
        }
    }
}
