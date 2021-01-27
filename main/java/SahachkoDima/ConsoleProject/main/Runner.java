package SahachkoDima.ConsoleProject.main;

import SahachkoDima.ConsoleProject.controller.*;
import SahachkoDima.ConsoleProject.repository.*;
import SahachkoDima.ConsoleProject.view.*;

public class Runner {
	RegionRepository regionRepo = new JavaIORegionRepository();
	PostRepository postRepo = new JavaIOPostRepository();
	WriterRepository writerRepo = new JavaIOWriterRepository();
	RegionController regionController = new RegionController(regionRepo);
	PostController postController = new PostController(postRepo);
	WriterController writerController = new WriterController(regionRepo, postRepo, writerRepo);
	RegionView regionView = new RegionView(regionController);
	PostView postView = new PostView(postController);
	WriterView writerView = new WriterView(writerController);
	
	public void startConsole() {
		regionView.startConsole();
		postView.startConsole();
		writerView.startConsole();
	}
	
	public void workWithRegions() {
		regionView.startConsole();
	}
	
	public void workWithPosts() {
		postView.startConsole();
	}
	
	public void workWithWriters() {
		writerView.startConsole();
	}
}
