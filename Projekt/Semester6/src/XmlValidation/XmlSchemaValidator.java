package XmlValidation;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class XmlSchemaValidator {

    private static final List<String> xmlPaths = Arrays.asList("ITT/XtensibleMarkupLanguage/lectureDescription.xml", "ITT/XtensibleMarkupLanguage/strongLectureDescription.xml", "ITT/XtensibleMarkupLanguage/soccer.xml" , "ITT/XtensibleMarkupLanguage/petrolPrice.xml");

        public static void main(String[] args) {

            /*for (String string : xmlPaths) {
                if (isValidXML(string)) {
                    final String fileName = removeSubstring(string);
                    System.out.println("the XML File " + fileName + " has passed validation successfully ");
                }
            }
            */


            try {
                for (String string : xmlPaths) {
                    if (isValidXML(string)) {
                        final String fileName = removeSubstring(string);
                        System.out.println("the XML File " + fileName + " has passed validation successfully ");
                    } else{
                        System.out.println("Error for " + string + " occured");
                    }
                }
            } catch (Exception e){
                System.out.println("the XML File " + e + " Exception occured");
            }
        }


    private static String removeSubstring(String str) {
        int startIndex = str.indexOf("ITT/XtensibleMarkupLanguage/");
        int stopIndex = startIndex + "ITT/XtensibleMarkupLanguage/".length();
        StringBuilder builder = new StringBuilder(str);
        builder.delete(startIndex, stopIndex);
        return builder.toString();
    }

    public static boolean isValidXML(String xmlFilePath) {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setValidating(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(xmlFilePath);
            doc.getDocumentElement().normalize();

        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }


    public static boolean isValidXMLAgainstXsdSchema(String xsdPath, String xmlPath){
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | org.xml.sax.SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
}
