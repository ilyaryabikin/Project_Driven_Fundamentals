package engine.domain;

@FunctionalInterface
public interface Rule {
    void perform(Facts facts);
}
