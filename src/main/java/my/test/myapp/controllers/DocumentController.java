package my.test.myapp.controllers;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import my.test.myapp.domainClasses.Document;
import my.test.myapp.repositories.DocumentRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
	private DocumentRepository documentRepository;
	
	private DocumentController(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}

	@GetMapping("/{documentId}")
	public Iterable<Document> getDocumentById(@PathVariable Long documentId) {
		return documentRepository.getDocumentById(documentId);
	}
	
	@GetMapping("/{documentId}/data-points")
	public Iterable<String> getDataPointsByDocumentId(@PathVariable Long documentId) {
		return documentRepository.getDataPointsByDocumentId(documentId);
	}
	
	@PostMapping("/upload")
	public String uploadAndParseFile(@RequestBody String file) {
		try {
			InputStream stream = new FileInputStream("src/main/resources/input.pdf");
			return extractContentUsingParser(stream);
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (TikaException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (SAXException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String detectDocTypeUsingFacade(InputStream stream) throws IOException {
		Tika tika = new Tika();
		String mediaType = tika.detect(stream);
		return mediaType;
	}
	
	public String extractContentUsingParser(InputStream stream) throws IOException, TikaException, SAXException {
		Parser parser = new AutoDetectParser();
//		BodyContentHandler handler = new BodyContentHandler();
		ToXMLContentHandler handler = new ToXMLContentHandler();
		Metadata metadata = new Metadata();
		ParseContext context = new ParseContext();

		parser.parse(stream, handler, metadata, context);
		return handler.toString();
	}
}
