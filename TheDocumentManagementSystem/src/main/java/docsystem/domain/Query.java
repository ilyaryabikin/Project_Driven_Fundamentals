package docsystem.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Query implements Predicate<Document> {
    private final Map<String, String> clauses;

    private Query(Map<String, String> clauses) {
        this.clauses = clauses;
    }

    static Query parse(String query) {
        return new Query(Arrays.stream(query.split(","))
                .map(string -> string.split(":"))
                .collect(Collectors.toMap(x -> x[0], x -> x[1])));
    }

    @Override
    public boolean test(Document document) {
        return clauses.entrySet().stream().allMatch(entry -> {
            String documentValue = document.getAttribute(entry.getKey());
            String queryValue = entry.getValue();
            return documentValue != null && documentValue.contains(queryValue);
        });
    }
}
