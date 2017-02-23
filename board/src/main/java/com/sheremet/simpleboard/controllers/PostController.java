package com.sheremet.simpleboard.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sheremet.simpleboard.models.Post;
import com.sheremet.simpleboard.models.PostService;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String showPosts(ModelMap modelMap){
		List<Post> list = postService.getAllPosts();
		modelMap.addAttribute("posts", list);
		return "index";
	}
	@RequestMapping(value="/posts",method = RequestMethod.POST)
	public String addPost(@ModelAttribute Post post){
		System.out.println(post.getText());
		postService.addPost(post);
		return "added";
	}
	@RequestMapping(value="/posts/{id}",method = RequestMethod.DELETE)
	public @ResponseBody Map deletePost(@PathVariable(name="id") long id){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			postService.deletePostById(id);
			map.put("success", true);
			map.put("redirect", "deleted");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}
	@RequestMapping(value="/deleted",method = RequestMethod.GET)
	public String afterDeletePost(){
		return "deleted";
	}
}
