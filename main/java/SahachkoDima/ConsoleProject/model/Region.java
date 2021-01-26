package SahachkoDima.ConsoleProject.model;

public class Region {
	private Long id;
	private String name;
	
	public Region(String name) {
		this.name = name;
	}
	
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

	@Override
	public String toString() {
		return id + "." +  name;
	}
}
