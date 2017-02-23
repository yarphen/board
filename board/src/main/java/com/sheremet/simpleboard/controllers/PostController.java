package com.sheremet.simpleboard.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sheremet.simpleboard.auth.Authority;
import com.sheremet.simpleboard.models.Post;
import com.sheremet.simpleboard.models.PostService;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String showPosts(ModelMap modelMap){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Post> list = postService.getAllPosts();
		modelMap.addAttribute("loggedin", auth.getAuthorities().contains(new Authority("ROLE_USER")));
		modelMap.addAttribute("username", auth.getName());
		modelMap.addAttribute("posts", list);
		return "index";
	}
	@Secured(value={"ROLE_USER"})
	@RequestMapping(value="/posts",method = RequestMethod.POST)
	public ModelAndView addPost(@Valid @ModelAttribute  Post  post, BindingResult result){
		if (!result.hasErrors()){
			System.out.println(post.getText());
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			post.setAuthor(auth.getName());
			postService.addPost(post);
			return new ModelAndView("added");
		}else{
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName(showPosts(modelAndView.getModelMap()));
			modelAndView.getModelMap().addAttribute("message", "Post must be 10+ letters");
			return modelAndView;
		}
	}
	@Secured(value={"ROLE_USER"})
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
	@Secured(value={"ROLE_USER"})
	@RequestMapping(value="/deleted",method = RequestMethod.GET)
	public String afterDeletePost(){
		return "deleted";
	}
}
