package docsystem.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static docsystem.constant.Attributes.PATH;

public class TextFile {
    private final Map<String, String> attributes;
    private final List<String> lines;

    public TextFile(File file) throws IOException {
        attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());
        lines = Files.readAllLines(file.toPath());
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public int addLines(int start, Predicate<String> isEnd, String attributeName) {
        StringBuilder accumulator = new StringBuilder();
        int lineNumber;
        for (lineNumber = start; lineNumber < lines.size(); lineNumber++) {
            String line = lines.get(lineNumber);
            if (isEnd.test(line)) {
                break;
            }

            accumulator.append(line);
            accumulator.append("\n");
        }
        attributes.put(attributeName, accumulator.toString().trim());
        return lineNumber;
    }

    public void addLineSuffix(String prefix, String attributeName) {
        for (String line: lines) {
            if (line.startsWith(prefix)) {
                attributes.put(attributeName, line.substring(prefix.length()));
                break;
            }
        }
    }
}
