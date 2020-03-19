package docsystem.importer;

import docsystem.domain.Document;
import docsystem.domain.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static docsystem.constant.Attributes.*;

public class LetterImporter implements Importer {
    private static final String NAME_PREFIX = "Dear ";

    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);

        int lineNumber = textFile.addLines(2, String::isEmpty, ADDRESS);
        textFile.addLines(lineNumber + 1, line -> line.startsWith("regards,"), BODY);

        Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, LETTER_TYPE);
        return new Document(attributes);
    }
}
