package com.sheremet.simpleboard.models;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

public interface PostService {

	List<Post> getAllPosts();

	void addPost(Post body);
	
	@PreAuthorize ("@postService.getPostById(#id).getAuthor() == authentication.name")
	void deletePostById(long id);
	
	Post getPostById(long id);

}
