package SahachkoDima.ConsoleProject.regionRelated;

import java.util.List;

public class Controller {
	RegionRepository repository;
	
	Controller() {
		repository = new RegionRepository();
	}
	
	void addRegion(String name) {
		Region region = new Region(name);
		repository.save(region);
	}
	
	void updateRegion(long id, String name) {
		Region region = new Region(name);
		region.setId(id);
		repository.update(region);
	}
	
	List<Region> getAllRegions() {
		return repository.getAll();
	}
	
	Region getRegionById(long id) {
		return repository.getById(id);
	}
	
	void deleteRegion(long id) {
		repository.deleteById(id);
	}
}
