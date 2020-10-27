package my.test.myapp.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import my.test.myapp.domainClasses.DataPoint;
import my.test.myapp.repositories.DataPointRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/data-points")
public class DataPointController {
	private DataPointRepository dataPointRepository;
	
	public DataPointController(DataPointRepository dataPointRepository) {
		this.dataPointRepository = dataPointRepository;
	}

	@RequestMapping(value = "/{dataPointName}", method = RequestMethod.GET)
	@ResponseBody
	public Optional<DataPoint> findByName(@PathVariable String dataPointName) {
		return dataPointRepository.findByName(dataPointName);
	}

	@RequestMapping(value = "/{dataPointName}/add-to-dataset", method = RequestMethod.POST)
	@ResponseBody
	public String addToDataset(@PathVariable String dataPointName, @RequestBody String body) {
		dataPointRepository.save(new DataPoint(dataPointName, body));
		return "Added to dataset";
	}

}
