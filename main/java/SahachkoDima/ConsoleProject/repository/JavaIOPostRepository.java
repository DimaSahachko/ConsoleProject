package SahachkoDima.ConsoleProject.repository;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

import com.google.gson.Gson;

import SahachkoDima.ConsoleProject.model.Post;

public class JavaIOPostRepository implements PostRepository {
	Path postsStorage = Paths.get("src\\main\\resources\\files\\posts.json");
	Gson gson = new Gson();
	
	@Override
	public List<Post> getAll() {
		List<Post> allPosts = new ArrayList<>();
		if(Files.notExists(postsStorage)) {
			try {
				Files.createFile(postsStorage);
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}
		try(Stream<String> stream = Files.lines(postsStorage)) {
			stream.map(line -> gson.fromJson(line, Post.class)).forEach(post -> allPosts.add(post));
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		return allPosts;
	}

	@Override
	public Post getById(Long id) {
		Post post = null;
		try(Stream<String> stream = Files.lines(postsStorage)) {
			Optional<Post> optionalPost = stream.map(line -> gson.fromJson(line, Post.class))
					.filter(currentPost -> currentPost.getId() == id).findFirst();
			if(optionalPost.isPresent()) {
				post = optionalPost.get();
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		return post;
	}

	@Override
	public Post save(Post post) {
		long nextEmptyId = 0;
		List<Post> allPosts = new ArrayList<>();
		try{
			if (Files.notExists(postsStorage)) {
				Files.createFile(postsStorage);
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		try(Stream<String> stream = Files.lines(postsStorage)) {
			stream.map(line -> gson.fromJson(line, Post.class)).forEach(postObj -> allPosts.add(postObj));
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		if(allPosts.size() == 0) {
			nextEmptyId = 1;
		} else {
			long counter = 0;
			for(Post currentPost : allPosts) {
				if(currentPost.getId() > counter) {
					counter = currentPost.getId();
					nextEmptyId = counter + 1;
				}
			}
		}
		post.setId(nextEmptyId);
		post.setCreated(new Date());
		String postJsonRepresentation = gson.toJson(post);
		try{
			Files.write(postsStorage, (postJsonRepresentation + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		return post;
	}

	@Override
	public Post update(Post post) {
		List<Post> allPostsBeforeUpdating = getAll();
		List<Post> allPostsAfterUpdating = new ArrayList<>();
		for(Post currentPost : allPostsBeforeUpdating) {
			if(currentPost.getId().equals(post.getId())) {
				post.setCreated(currentPost.getCreated());
				post.setUpdated(new Date());
				allPostsAfterUpdating.add(post);
				continue;
			}
			allPostsAfterUpdating.add(currentPost);
		}
		if(allPostsBeforeUpdating.equals(allPostsAfterUpdating)) {
			return null;
		} else {
			try {
				Files.delete(postsStorage);
			} catch (IOException exc) {
				exc.printStackTrace();
			}
			allPostsAfterUpdating.stream().map(currentPost -> gson.toJson(currentPost))
			.forEach(jsonPost -> {
					try {
						Files.write(postsStorage, (jsonPost + System.lineSeparator()).getBytes(), 
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
					} catch (IOException exc) {
						exc.printStackTrace();
					}
				});
		}
		return post;
	}

	@Override
	public void deleteById(Long id) {
		List<Post> allPostsBeforeUpdating = getAll();
		List<Post> allPostsAfterUpdating = new ArrayList<>();
		for(Post currentPost : allPostsBeforeUpdating) {
			if(currentPost.getId().equals(id)) {
				continue;
			}
			allPostsAfterUpdating.add(currentPost);
		}
		if(allPostsBeforeUpdating.equals(allPostsAfterUpdating)) {
			System.out.println("There is no region with wuch id");
		} else {
			try {
				Files.delete(postsStorage);
			} catch (IOException exc) {
				exc.printStackTrace();
			}
			allPostsAfterUpdating.stream().map(currentPost -> gson.toJson(currentPost))
			.forEach(jsonPost -> {
					try {
						Files.write(postsStorage, (jsonPost + System.lineSeparator()).getBytes(), 
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
					} catch (IOException exc) {
						exc.printStackTrace();
					}
				});
		}
	}

}
