package my.test.myapp.domainClasses;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class DataPoint {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

	public DataPoint() {
		// just empty
	}
	
	public DataPoint(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Relationship(type = "PARENT", direction = Relationship.INCOMING)
	public Set<Document> documents;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
}
