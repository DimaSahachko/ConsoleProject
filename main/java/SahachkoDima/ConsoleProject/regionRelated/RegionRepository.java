package SahachkoDima.ConsoleProject.regionRelated;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;
import com.google.gson.Gson;

public class RegionRepository {
	private Path regionsStorage;
	private Gson gson;
	
	RegionRepository() {
		regionsStorage = Paths.get("src\\main\\resources\\files\\regions.json");
		gson = new Gson();
	}
	
	List<Region> getAll() {
		List<Region> allRegions = new ArrayList<>();
		try(Stream<String> stream = Files.lines(regionsStorage)) {
			stream.forEach(region -> allRegions.add(gson.fromJson(region, Region.class)));
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		return allRegions;
	}
	
	Region getById(Long id) {
		Region regionById = null;
		try(Stream<String> stream = Files.lines(regionsStorage)) {
			Optional<Region> optionalRegion = stream.map(stringRegion -> gson.fromJson(stringRegion, Region.class)).
				filter(region -> region.getId()==id).findFirst();
			if(optionalRegion.isPresent()) {
					regionById = optionalRegion.get();
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		if(regionById == null) {
		System.out.println("There is no region with such id");
		return null;
		} else {
			return regionById;
		}
	}
	
	Region save(Region region) {
		if (Files.notExists(regionsStorage)) {
			try {
				Files.createFile(regionsStorage);
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}	
			String regionJsonRepresentation = gson.toJson(region);
		try {
			Files.write(regionsStorage, (regionJsonRepresentation + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		return region;
	}
	
	Region update(Region region) {
		List<Region> allRegionsBeforeUpdating = getAll();
		List<Region> allRegionsAfterUpdating = new ArrayList<>();
		for(Region currentRegion : allRegionsBeforeUpdating) {
			if(currentRegion.getId().equals(region.getId())) {
				allRegionsAfterUpdating.add(region);
				continue;
			}
			allRegionsAfterUpdating.add(currentRegion);
		}
		if (allRegionsBeforeUpdating.equals(allRegionsAfterUpdating)) {
			System.out.println("There is no region with such id");
			return region;
		} else {
			try { 
				Files.delete(regionsStorage);
				} catch(IOException exc ) {
				exc.printStackTrace();
				}
			allRegionsAfterUpdating.stream().map(regn -> gson.toJson(regn)).
				forEach(jsonRegion -> {
					try{
						Files.write(regionsStorage, (jsonRegion + System.lineSeparator()).getBytes(),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
					} catch (IOException exc) {
						exc.printStackTrace();
					}
				});
		}
		return region;
	}
	
	void deleteById(Long id) {
		List<Region> allRegionsBeforeDeleting = getAll();
		List<Region> allRegionsAfterDeleting = new ArrayList<>();
		for(Region currentRegion : allRegionsBeforeDeleting) {
			if(currentRegion.getId().equals(id)) {
				continue;
			}
			allRegionsAfterDeleting.add(currentRegion);
		}
		if (allRegionsBeforeDeleting.equals(allRegionsAfterDeleting)) {
			System.out.println("There is no region with such id");
		} else {
			try { 
				Files.delete(regionsStorage);
				} catch(IOException exc ) {
				exc.printStackTrace();
				}
			allRegionsAfterDeleting.stream().map(region -> gson.toJson(region)).
				forEach(jsonRegion -> {
					try{
						Files.write(regionsStorage, (jsonRegion + System.lineSeparator()).getBytes(),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
					} catch (IOException exc) {
						exc.printStackTrace();
					}
				});
		}
	}
}
