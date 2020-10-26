package my.test.myapp.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.test.myapp.domainClasses.DataPoint;
import my.test.myapp.repositories.DataPointRepository;

@RestController
@RequestMapping("/api/data-points")
public class DataPointController {
	private DataPointRepository dataPointRepository;
	
	public DataPointController(DataPointRepository dataPointRepository) {
		this.dataPointRepository = dataPointRepository;
	}
	
	@PostMapping("/{dataPointId}/add-to-dataset")
	public DataPoint addToDataset(@PathVariable Long dataPointId, @RequestBody String body) {
		return dataPointRepository.save(new DataPoint(dataPointId, body));
	}
//	POST api/data-points/{DataPointID}/add-to-dataset – in format: label -> text content. 

}
