package SahachkoDima.ConsoleProject.regionRelated;

public class Region {
	private Long id;
	private String name;
	static long counter;
	
	Region(String name) {
		this.name = name;
		counter++;
		id = new Long(counter);
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return id + "-" +  name;
	}
}
