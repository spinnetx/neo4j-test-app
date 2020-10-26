package my.test.myapp.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import my.test.myapp.domainClasses.DataPoint;

public interface DataPointRepository extends Neo4jRepository<DataPoint, Long> {

}