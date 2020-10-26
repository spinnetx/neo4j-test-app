package my.test.myapp.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import my.test.myapp.domainClasses.Document;

public interface DocumentRepository extends Neo4jRepository<Document, Long> {

	@Query("match (dd:Document) where dd.documentId = {0} return dd")
	Iterable<Document> getDocumentById(Long documentId);
	
	@Query("match (dd:Document) - [p:PARENT] -> (dp:DataPoint) where dd.documentId = {0} return dp.description")
	Iterable<String> getDataPointsByDocumentId(Long documentId);
}
