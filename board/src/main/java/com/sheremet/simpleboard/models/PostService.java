package com.sheremet.simpleboard.models;

import java.util.List;

public interface PostService {

	List<Post> getAllPosts();

	void addPost(Post body);
	
	void deletePostById(long id);
	
	Post getPostById(long id);

}
