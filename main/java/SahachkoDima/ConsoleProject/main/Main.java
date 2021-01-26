package SahachkoDima.ConsoleProject.main;

import SahachkoDima.ConsoleProject.controller.RegionController;
import SahachkoDima.ConsoleProject.repository.JavaIORegionRepository;
import SahachkoDima.ConsoleProject.view.RegionView;

public class Main {

	public static void main(String[] args) {
		RegionController regionController = new RegionController(new JavaIORegionRepository());
		RegionView ui = new RegionView(regionController);
		ui.startConsole();
		
	}

}
