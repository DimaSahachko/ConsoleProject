package SahachkoDima.ConsoleProject.controller;

import java.util.Date;
import java.util.List;

import SahachkoDima.ConsoleProject.model.Post;
import SahachkoDima.ConsoleProject.repository.PostRepository;

public class PostController {
	PostRepository repository;
	
	public PostController(PostRepository repository) {
		this.repository = repository;
	}
	
	public Post addPost(String content) {
		Post post = new Post(content);
		return repository.save(post);
	}
	
	public Post updatePost(long id, String content) {
		Post post = new Post(content);
		post.setId(id);
		return repository.update(post);
	}
	
	public List<Post> getAllPosts() {
		return repository.getAll();
	}
	
	public Post getPostById(long id) {
		return repository.getById(id);
	}
	
	public void deleteById(long id) {
		repository.deleteById(id);
	}
}
