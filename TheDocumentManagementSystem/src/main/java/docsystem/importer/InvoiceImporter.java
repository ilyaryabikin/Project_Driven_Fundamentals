package docsystem.importer;

import docsystem.domain.Document;
import docsystem.domain.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static docsystem.constant.Attributes.*;

public class InvoiceImporter implements Importer {
    private static final String NAME_PREFIX = "Dear ";
    private static final String AMOUNT_PREFIX = "Amount: ";

    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);

        Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, INVOICE_TYPE);
        return new Document(attributes);
    }
}
