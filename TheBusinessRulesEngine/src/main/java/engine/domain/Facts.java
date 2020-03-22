package engine.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Facts {
    private final Map<String, String> facts = new HashMap<>();

    public String getFact(String name) {
        return facts.get(name);
    }

    public void addFact(String name, String value) {
        facts.put(name, value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facts);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Facts other = ((Facts) obj);
        return other.facts.equals(this.facts);
    }
}
