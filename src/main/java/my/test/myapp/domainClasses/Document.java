package my.test.myapp.domainClasses;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Document {

	@Id
	@GeneratedValue
	private Long id;
	private String name;

	public Document() {
		// just empty
	}

	public Document(String name) {
		this.name = name;
	}

	@Relationship(type = "PARENT")
	public Set<DataPoint> dataPoints;

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

	public Set<DataPoint> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(Set<DataPoint> dataPoints) {
		this.dataPoints = dataPoints;
	}
}
