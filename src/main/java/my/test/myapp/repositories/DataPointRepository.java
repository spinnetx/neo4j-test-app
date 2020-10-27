package my.test.myapp.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import my.test.myapp.domainClasses.DataPoint;

import java.util.Optional;

public interface DataPointRepository extends Neo4jRepository<DataPoint, Long> {
    Optional<DataPoint> findByName(String name);
}