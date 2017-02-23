package com.sheremet.simpleboard.models;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository
public class PostServiceImpl implements PostService {

	@Autowired
	private SessionFactory sessionFactory;

	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	public List<Post> getAllPosts() {
		return (List<Post>)currentSession().createCriteria(Post.class).list();
	}

	public void addPost(Post post) {
		currentSession().save(post);
	}
	public void deletePostById(long id) {
		currentSession().delete(getPostById(id));
	}
	public Post getPostById(long id) {
		return (Post) currentSession().get(Post.class, id);
	}

}
