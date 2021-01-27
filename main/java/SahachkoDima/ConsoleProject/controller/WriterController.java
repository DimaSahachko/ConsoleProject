package SahachkoDima.ConsoleProject.controller;

import java.util.List;

import SahachkoDima.ConsoleProject.model.Post;
import SahachkoDima.ConsoleProject.model.Region;
import SahachkoDima.ConsoleProject.model.Writer;
import SahachkoDima.ConsoleProject.repository.*;

public class WriterController {
	RegionRepository regionRepo;
	PostRepository postRepo;
	WriterRepository writerRepo;
	
	public WriterController(RegionRepository regionRepo, PostRepository postRepo, WriterRepository writerRepo) {
		this.regionRepo = regionRepo;
		this.postRepo = postRepo;
		this.writerRepo = writerRepo;
	}
	
	public Writer addWriter(String firstName, String lastName, List<Post> posts, Region region) {
		Writer writer = new Writer(firstName, lastName);
		writer.setPosts(posts);
		writer.setRegion(region);
		return writerRepo.save(writer);
	}
	
	public Writer updateWriterFirstName(long id, String firstName) {
		Writer writer = writerRepo.getById(id);
		writer.setFirstName(firstName);
		return writerRepo.update(writer);
	}
	
	public Writer updateWriterLastName(long id, String lastName) {
		Writer writer = writerRepo.getById(id);
		writer.setLastName(lastName);
		return writerRepo.update(writer);
	}
	
	public Writer updateWriterPosts(long id, List<Post> posts) {
		Writer writer = writerRepo.getById(id);
		writer.setPosts(posts);
		return writerRepo.update(writer);
	}
	
	public Writer updateWriterRegion(long id, Region region) {
		Writer writer = writerRepo.getById(id);
		writer.setRegion(region);
		return writerRepo.update(writer);
	}
	
	public List<Writer> getAllWriters() {
		return writerRepo.getAll();
	}
	
	public Writer getWriterById(long id) {
		return writerRepo.getById(id);
	}
	
	public void deleteWriterById(long id) {
		writerRepo.deleteById(id);
	}
	
	public List<Post> getAvailablePosts() {
		return postRepo.getAll();
	}
	
	public Post getPostById(long id) {
		return postRepo.getById(id);
	}
	
	public List<Region> getAvailableRegions() {
		return regionRepo.getAll();
	}
	
	public Region getRegionById(long id) {
		return regionRepo.getById(id);
	}
}
