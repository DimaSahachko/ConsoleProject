package SahachkoDima.ConsoleProject.regionRelated;

public class Main {

	public static void main(String[] args) {
		RegionRepository storage = new RegionRepository();
		
		Region us = new Region("US");
		Region eu = new Region("EU");
		Region asia = new Region("ASIA");
		Region africa = new Region("AFRICA");
		
		storage.save(us);
		storage.save(eu);
		storage.save(asia);
		storage.save(africa);
		
		System.out.println(storage.getAll());
		System.out.println(storage.getById(2L));
		System.out.println(storage.getById(4L));
		
		System.out.println("\n===============\n");
	
		asia.setName("ASIA REGION");
		africa.setName("AFRICA REGION");
		
		storage.update(asia);
		System.out.println(storage.getAll());
		storage.update(africa);
		System.out.println(storage.getAll());
		
		System.out.println("\n===============\n");
		
		storage.deleteById(1L);
		System.out.println(storage.getAll());
	}

}
