package SahachkoDima.ConsoleProject.controller;

import java.util.List;

import SahachkoDima.ConsoleProject.model.Region;
import SahachkoDima.ConsoleProject.repository.JavaIORegionRepository;
import SahachkoDima.ConsoleProject.repository.RegionRepository;

public class RegionController {
	RegionRepository repository;
	
	public RegionController(RegionRepository repository) {
		this.repository = repository;
	}
	
	public Region addRegion(String name) {
		Region region = new Region(name);
		return repository.save(region);
	}
	
	public Region updateRegion(long id, String name) {
		Region region = new Region(name);
		region.setId(id);
		return repository.update(region);
	}
	
	public List<Region> getAllRegions() {
		return repository.getAll();
	}
	
	public Region getRegionById(long id) {
		return repository.getById(id);
	}
	
	public void deleteRegion(long id) {
		repository.deleteById(id);
	}
}
