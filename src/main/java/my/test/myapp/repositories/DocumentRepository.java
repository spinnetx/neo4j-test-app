package my.test.myapp.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import my.test.myapp.domainClasses.Document;

import java.util.Optional;

public interface DocumentRepository extends Neo4jRepository<Document, Long> {
	Optional<Document> findByName(String name);
}
