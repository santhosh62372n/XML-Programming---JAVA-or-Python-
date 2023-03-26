import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DomParserSchema {

	public static void main(String args[]) {
		String xmlSchemaFilesName = "ACCOUNTS_XML_Schema.xsd";
		String xmlFileName = "ACCOUNTS_XML_DOM.xml";
		Schema schema = loadXMLSchemaFile(xmlSchemaFilesName);
		Document document = null;
		document = parseXmlDOMFile(xmlFileName);
		validateXmlAgainstXmlSchema(schema, document);
	}

	public static Schema loadXMLSchemaFile(String xmlSchemaFilesName) {
		System.out.println("Begin loadXMLSchemaFile");
		Schema schema = null;
		try {
			String xmlSchemaNameSpaceURI = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			SchemaFactory schemaFactory = SchemaFactory.newInstance(xmlSchemaNameSpaceURI);
			schema = schemaFactory.newSchema(new File(xmlSchemaFilesName));
			System.out.println("Loading XML Schema File is successful");
		} catch (SAXException e) {
			System.out.println("SAXException in loadXMLSchemaFile:: "+e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception in loadXMLSchemaFile:: "+e.getMessage());
		}
		System.out.println("End loadXMLSchemaFile");
		return schema;
	}

	public static Document parseXmlDOMFile(String xmlName) {
		System.out.println("Begin parseXmlDOMFile");
		  Document document = null;
			try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				document = documentBuilder.parse(new File(xmlName));
			} catch (SAXException e) {
				System.out.println("SAXException in parseXmlDOMFile:: "+e.getMessage());
			} catch (IOException e) {
				System.out.println("IOException in parseXmlDOMFile:: "+e.getMessage());
			} catch (Exception e) {
				System.out.println("Exception in parseXmlDOMFile:: "+e.getMessage());
			}
			System.out.println("Parsing XML DOM File is successful");

		System.out.println("End parseXmlDOMFile");
		return document;
	}

	public static void validateXmlAgainstXmlSchema(Schema schema, Document document) {
		System.out.println("Begin validateXmlAgainstXmlSchema");
		try {
			Validator validator = schema.newValidator();
			validator.validate(new DOMSource(document));
			System.out.println("Validating XML Againt XML Schema is successful");
		} catch (SAXException e) {
			System.out.println("SAXException in validateXmlAgainstXmlSchema:: "+e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException in validateXmlAgainstXmlSchema:: "+e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception in validateXmlAgainstXmlSchema:: "+e.getMessage());
		}
		System.out.println("End validateXmlAgainstXmlSchema");
	}
}
