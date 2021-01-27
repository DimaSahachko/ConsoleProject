package SahachkoDima.ConsoleProject.view;

import java.util.*;

import SahachkoDima.ConsoleProject.controller.WriterController;
import SahachkoDima.ConsoleProject.model.Post;
import SahachkoDima.ConsoleProject.model.Region;
import SahachkoDima.ConsoleProject.model.Writer;

public class WriterView {
	
	private WriterController controller;
	private Scanner sc = new Scanner(System.in);
	
	public WriterView(WriterController controller) {
		this.controller = controller;
	}
	
	public void startConsole() {
		System.out.println("Choose the available option. Type:");
		System.out.println("- \"add\" - to add a new writer;");
		System.out.println("- \"update\" - to update the existing writer;");
		System.out.println("- \"all\" - to print the list of all existing writers");
		System.out.println("- \"get\" - to get the writer by their id;");
		System.out.println("- \"delete\" - to delete the writer by their id");
		
		label:
		while(true) {
			String input = sc.nextLine();
			switch(input) {
			case "add" :
				addWriter();
				break;
			case "update" :
				updateWriter();
				break;
			case "all" :
				allWriters();
				break;
			case "get" :
				getWriterById();
				break;
			case "delete" :
				deleteWriter();
				break;
			default :
				System.out.println("The input is wrong. Type once again.");
				continue label;
			}
			System.out.println("Do you want to continue? (\"yes\"\\\"no\")");
			String doMore = sc.nextLine();
			if(doMore.equalsIgnoreCase("yes")) {
				startConsole();
				break;
			} else {
				System.out.println("Have a nice day. Good buy");
				break;
			}
		}
	}
	
	private void addWriter() {
		String firstName = setWriterFirstName();
		String lastName = setWriterLastName();
		List<Post> thisWriterPosts = setWriterPosts();
		Region region = setWriterRegion();
		Writer writer = controller.addWriter(firstName, lastName, thisWriterPosts, region);
		System.out.println("Writer was added\n" + writer);
	}
	
	private String setWriterFirstName() {
		System.out.println("Type the writer's first name");
		String firstName = sc.nextLine();
		return firstName;
	}
	
	private String setWriterLastName() {
		System.out.println("Type the writer's last name");
		String lastName = sc.nextLine();
		return lastName;
	}
	
	private List<Post> setWriterPosts() {
		System.out.println("Form writer's posts list. Choose from the available posts. ");
		List<Post> thisWriterPosts = new ArrayList<>();
		List<Post> allPosts = controller.getAvailablePosts();
		allPosts.stream().forEach(System.out::println);
		outer:
		while(true) {
			System.out.println("Just type the id of the post which you want to add");
			try {
				long id = Long.parseLong(sc.nextLine());
				Post post = controller.getPostById(id);
				if(post == null) {
					System.out.println("There is no post with such id");
				} else {
					thisWriterPosts.add(post);
					System.out.println("Post \"" + post + "\" was added");
				}
			} catch (NumberFormatException exc) {
				System.out.println("Inappropriate input. Try once again");
				continue outer;
			}
			System.out.println("Add another post? (\"yes\"\\\"no\")");
			String addAnotherPost = sc.nextLine();
			if(!(addAnotherPost.equalsIgnoreCase("yes"))) {
				break;
			}
		}
		return thisWriterPosts;
	}
	
	private Region setWriterRegion() {
		System.out.println("Choose the writer's region from this list");
		Region region = null;
		List<Region> regions = controller.getAvailableRegions();
		regions.stream().forEach(System.out::println);
		label:
		while(true) {
			System.out.println("Just type the region's id");
			try {
				long id = Long.parseLong(sc.nextLine());
				region = controller.getRegionById(id);
				if(region == null) {
					System.out.println("There is no region with such id. Choose another one.");
					continue label;
				} else {
					System.out.println("Region " + region + " was added");
				}
			} catch(NumberFormatException exc) {
				System.out.println("Inappropriate input. Try once again");
				continue label;
			}
			break;
		}
		return region;
	}
	
	private void updateWriter() {
		Writer writer = null;
		System.out.println("Type the id of the writer which you want to update");
		long id = 0;
		try {
			id = Long.parseLong(sc.nextLine());
			writer = controller.getWriterById(id);
			if (writer == null) {
				System.out.println("There is no writer with such id. Choose another one");
				updateWriter();
				return;
			}
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again");
			updateWriter();
			return;
		}
		
		label:
			while(true) {
				System.out.println("What do you want to update? Type:");
				System.out.println("- \"first name\" - to update first name;");
				System.out.println("- \"last name\" - to update last name;");
				System.out.println("- \"posts\" - to update posts;");
				System.out.println("- \"region\" - to update region.");
				String input = sc.nextLine();
				switch(input) {
				case "first name" :
					String firstName = setWriterFirstName();
					writer = controller.updateWriterFirstName(id, firstName);
					System.out.println("Writer was updated\n" + writer);
					break;
				case "last name" :
					String lastName = setWriterLastName();
					writer = controller.updateWriterLastName(id, lastName);
					System.out.println("Writer was updated\n" + writer);
					break;
				case "posts" :
					List<Post> posts = setWriterPosts();
					writer = controller.updateWriterPosts(id, posts);
					System.out.println("Writer was updated\n" + writer);
					break;
				case "region" :
					Region region = setWriterRegion();
					writer = controller.updateWriterRegion(id, region);
					System.out.println("Writer was updated\n" + writer);
					break;
				default:
					System.out.println("The input is wrong. Try once again");
					continue label;
				}	
				System.out.println("Do you want to update anything else of this writer? (\"yes\"/\"no\")");
				String doMore = sc.nextLine();
				if(doMore.equalsIgnoreCase("yes")) {
					continue label;
				} else {
					break;
				}
			}
	}
	
	private void allWriters() {
		List<Writer> allWriters = controller.getAllWriters();
		if(allWriters.size() == 0) {
			System.out.println("There are no posts so far");
		}
		allWriters.stream().forEach(System.out::println);
	}
	
	private void getWriterById() {
		System.out.println("Type the id of the writer which you want to get");
		try {
			long id = Long.parseLong(sc.nextLine());
			Writer writer = controller.getWriterById(id);
			if(writer == null) {
				System.out.println("There is no writer with such id");
			} else {
				System.out.println(writer);
			}
		} catch (NumberFormatException exc) {
			System.out.println("Inappropraite input. Try once again");
			getWriterById();
		}
	}
	
	private void deleteWriter() {
		System.out.println("Type the id of the writer which you want to delete");
		try {
			long id = Long.parseLong(sc.nextLine());
			controller.deleteWriterById(id);
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again");
			deleteWriter();
		}
	}
	
}
