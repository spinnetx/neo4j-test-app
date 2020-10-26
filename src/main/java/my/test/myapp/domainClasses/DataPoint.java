package my.test.myapp.domainClasses;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class DataPoint {
    @Id
    @GeneratedValue
    private Long id;
    private String description;

	public Long getId() {
		return id;
	}

	public DataPoint() {	
	}
	
	public DataPoint(String description) {
		super();
		this.description = description;
	}
	
	public DataPoint(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
