package engine.domain;

@FunctionalInterface
public interface Action {
    void execute(Facts facts);
}
