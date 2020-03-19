package docsystem.domain;

import java.util.Map;

public class Document {
    private final Map<String, String> attributes;

    public Document(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }
}
