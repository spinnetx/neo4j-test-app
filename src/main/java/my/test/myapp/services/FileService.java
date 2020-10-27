package my.test.myapp.services;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileService {

    public Map<String, String> extractContentUsingParser() {
        Map<String, String> data = new HashMap<>();
        try {
            InputStream stream = new FileInputStream("src/main/resources/input.pdf");

            Parser parser = new AutoDetectParser();
            ContentHandler handler = new ToXMLContentHandler();
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();

            parser.parse(stream, handler, metadata, context);

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(new InputSource(new StringReader(handler.toString())));
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile("//html/body/div/p");

            Object result = expr.evaluate(document, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;

            String content = "";
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                content = node.getTextContent();
                if (content.startsWith("1. Definitions.")) {
                    break;
                }
            }
            data.put("title", metadata.get("title"));
            data.put("content", content);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("error", e.getMessage());
        }
        return data;
    }
}
