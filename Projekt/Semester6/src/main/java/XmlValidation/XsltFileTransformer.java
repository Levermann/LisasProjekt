package XmlValidation;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;



public class XsltFileTransformer {


    public static void main(String[] args) {

        transformXml(Constants.LECTURE_DESCTIPTION_XML_PATH, Constants.LECTURE_DESCRIPTION_HTML_PATH, Constants.TRANSFORM_LECTURE_DESCRIPTION_XSL_PATH);
        //transformXml(Constants.MY_BOOK1_XML_PATH, Constants.MY_BOOK2_XML_PATH, Constants.TRANSFORM_BOOK_XSL_PATH);
    }


    public static void transformXml(String pathToXmlFile, String pathToCreatedXmlFile, String transformationFile) {
        Source source = new StreamSource(pathToXmlFile);
        Result result = new StreamResult(pathToCreatedXmlFile);
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer(
                    new StreamSource(transformationFile));
            t.transform(source, result);
        } catch (TransformerException ex) {
            System.err.println(ex.getMessage());
        }
    }
}