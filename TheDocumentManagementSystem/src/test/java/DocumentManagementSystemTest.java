import docsystem.domain.Document;
import docsystem.domain.DocumentManagementSystem;
import docsystem.exception.UnknownFileTypeException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static docsystem.constant.Attributes.*;

public class DocumentManagementSystemTest {
    private static final String RESOURCES = "src" + File.separator + "test" +
            File.separator + "resources" + File.separator;
    private static final String LETTER = RESOURCES + "patient.letter";
    private static final String REPORT = RESOURCES + "patient.report";
    private static final String XRAY = RESOURCES + "xray.jpg";
    private static final String INVOICE = RESOURCES + "patient.invoice";
    private static final String UNKNOWN = RESOURCES + "unknown.txt";
    private static final String JOE_BLOGGS = "Joe Bloggs";

    private DocumentManagementSystem system = new DocumentManagementSystem();

    @Test
    public void shouldImportFile() throws Exception {
        system.importFile(LETTER);
        Document document = systemsOnlyDocument();
        assertAttributeEquals(document, PATH, LETTER);
    }

    @Test
    public void shouldImportLetterAttributes() throws Exception {
        system.importFile(LETTER);
        Document document = systemsOnlyDocument();
        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, ADDRESS,
                "123 Fake Street\n" +
                        "Westminster\n" +
                        "London\n" +
                        "United Kingdom");
        assertAttributeEquals(document, BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                        "with Dr. Avaj from 29th December 2016 to 5th January 2017.");
        assertTypeIs("LETTER", document);
    }

    @Test
    public void shouldImportReportAttributes() throws Exception {
        system.importFile(REPORT);
        Document document = systemsOnlyDocument();
        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, BODY,
                "On 5th January 2017 I examined Joe's teeth.\n" +
                        "We discussed his switch from drinking Coke to Diet Coke.\n" +
                        "No new problems were noted with his teeth.");
        assertTypeIs("REPORT", document);
    }

    @Test
    public void shouldImportImageAttributes() throws Exception {
        system.importFile(XRAY);
        Document document = systemsOnlyDocument();
        assertAttributeEquals(document, WIDTH, "320");
        assertAttributeEquals(document, HEIGHT, "179");
        assertTypeIs("IMAGE", document);
    }

    @Test
    public void shouldImportInvoiceAttributes() throws Exception {
        system.importFile(INVOICE);
        Document document = systemsOnlyDocument();
        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, AMOUNT, "$100");
        assertTypeIs("INVOICE", document);
    }

    @Test
    public void shouldBeAbleToSearchFilesByAttributes() throws Exception {
        system.importFile(LETTER);
        system.importFile(REPORT);
        system.importFile(XRAY);
        List<Document> documents = system.search("patient:Joe,body:Diet Coke");
        Assert.assertThat(documents, Matchers.hasSize(1));
        assertAttributeEquals(documents.get(0), PATIENT, JOE_BLOGGS);
        assertAttributeEquals(documents.get(0), BODY,
                "On 5th January 2017 I examined Joe's teeth.\n" +
                        "We discussed his switch from drinking Coke to Diet Coke.\n" +
                        "No new problems were noted with his teeth.");
        assertTypeIs("REPORT", documents.get(0));
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldNotImportMissingFile() throws Exception {
        system.importFile("definitely_non_ExistentFile.unknown");
    }

    @Test(expected = UnknownFileTypeException.class)
    public void shouldNotImportUnknownFileType() throws Exception {
        system.importFile(UNKNOWN);
    }

    private void assertAttributeEquals(Document document,
                                       String attributeName,
                                       String expectedValue) {
        Assert.assertEquals("Document has the wrong value for " + attributeName,
                expectedValue,
                document.getAttribute(attributeName));
    }

    private void assertTypeIs(String type, Document document) {
        assertAttributeEquals(document, TYPE, type);
    }

    private Document systemsOnlyDocument() {
        List<Document> documents = system.contents();
        Assert.assertThat(documents, Matchers.hasSize(1));
        return documents.get(0);
    }
}
