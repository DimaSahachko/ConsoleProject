package SahachkoDima.ConsoleProject.regionRelated;

import java.util.Scanner;

public class UserInterface {
	private Controller controller = new Controller();
	private Scanner sc = new Scanner(System.in);
	
	public void startConsole() {
		System.out.println("Choose the available option. Type:");
		System.out.println("- \"add\" - to add new region;");
		System.out.println("- \"update\" - to update existing region;");
		System.out.println("- \"all\" - to print the list of all exising regions;");
		System.out.println("- \"get\" - to get the region by its id;");
		System.out.println("- \"delete\" - to delete region by its id;");
		
		label:
		while(true) {
			String input = sc.nextLine();
				switch(input) {
					case "add" : 
						add();
						break;
					case "update" : 
						update();
						break;
					case "all" :
						allRegions();
						break;
					case "get" : 
						getRegionById();
						break;
					case "delete" :
						deleteRegion();
						break;
					default :
						System.out.println("The input is wrong. Type once again");
						continue label;
				}
			System.out.println("Do you want to continue? (\"yes\"\\\"no\")");
			String doMore = sc.nextLine();
			if(doMore.equalsIgnoreCase("yes")) {
				startConsole();
			} else {
				System.out.println("Have a nice day. Good buy.");
				break;
			}
		}		
	}
	
	private void add() {
		System.out.println("Type the name of the region");
		String name = sc.nextLine();
		controller.addRegion(name);
	}
	
	private void update() {
		System.out.println("Type the id of the region which you want to update");
		try {
			Long id = Long.parseLong(sc.nextLine());
			System.out.println("Type the new name of the updating region");
			String name = sc.nextLine();
			controller.updateRegion(id, name);
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again.");
			update();
		}
	}
	
	private void allRegions() {
		System.out.println(controller.getAllRegions());
	}
	
	private void getRegionById() {
		System.out.println("Type the id of the region which you want to get");
		try {
			long id = Long.parseLong(sc.nextLine());
			System.out.println(controller.getRegionById(id));
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again.");
			getRegionById();
		}
	}
	
	private void deleteRegion() {
		System.out.println("Type the id of the region which you want to delete");
		try {
			long id = Long.parseLong(sc.nextLine());
			controller.deleteRegion(id);
			System.out.println("Region was deleted");
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again.");
			deleteRegion();
		}
	}
}
