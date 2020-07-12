package XmlValidation;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;



public class Transformation {

    public static final String PATH_TO_MYBOOK = "ITT/DocumentTypeDefinition/Book1.dtd";

    public static void main(String[] args) {

        Source source = new StreamSource(".\\src\\XSLT\\myBook.xml");
        Result result = new StreamResult(".\\src\\XSLT\\output.xml");
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer(
                    new StreamSource(".\\src\\XSLT\\transformToXML.xsl"));
            t.transform(source, result);
        } catch (TransformerException ex) {
            System.err.println(ex.getMessage());
        }
    }


}