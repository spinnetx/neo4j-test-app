package my.test.myapp.controllers;

import my.test.myapp.domainClasses.DataPoint;
import my.test.myapp.domainClasses.Document;
import my.test.myapp.repositories.DocumentRepository;
import my.test.myapp.services.FileService;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
	private final DocumentRepository documentRepository;
	private final FileService fileService;
	
	private DocumentController(DocumentRepository documentRepository,
							   FileService fileService) {
		this.documentRepository = documentRepository;
		this.fileService = fileService;
	}

	@RequestMapping(value = "/{documentName}", method = RequestMethod.GET)
	@ResponseBody
	public Optional<Document> findByName(@PathVariable String documentName) {
		return documentRepository.findByName(documentName);
	}
	
	@RequestMapping(value = "/{documentName}/data-points", method = RequestMethod.GET)
	@ResponseBody
	public String getDataPointsByDocumentName(@PathVariable String documentName) {
		Optional<Document> document = documentRepository.findByName(documentName);
		JSONObject json = new JSONObject();
		if (document.isPresent()) {
			document.get().getDataPoints().stream().forEach(item -> json.put(item.getName(), item.getDescription()));
		}
		return json.toString();
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String uploadAndParseFile() {
		Map<String, String> data = fileService.extractContentUsingParser();
		if (data.containsKey("error")) {
			return data.get("error");
		}

		Document doc = new Document(data.get("title"));
		DataPoint dataPoint = new DataPoint("Definitions", data.get("content"));
		doc.setDataPoints(new HashSet<>(Arrays.asList(dataPoint)));
		documentRepository.save(doc);
		return "Upload completed";
	}
}
