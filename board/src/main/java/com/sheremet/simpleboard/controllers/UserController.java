package com.sheremet.simpleboard.controllers;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sheremet.simpleboard.auth.Authority;
import com.sheremet.simpleboard.models.User;
import com.sheremet.simpleboard.models.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="/users",method = RequestMethod.POST)
	public ModelAndView addUser(@Valid @ModelAttribute User user, BindingResult result){
		ModelAndView  modelAndView = new ModelAndView();
		if (result.hasErrors()){
			modelAndView.getModel().put("message", "Check you are using 8+ lowercased letters in username and 8+ letters in password, including lowercased, uppercased, special symbols and numbers");
			modelAndView.setViewName("signup");
		}else{
			try{
				userService.getUserByName(user.getUsername());
				System.out.println("Username already exists!");
				modelAndView.getModel().put("message", "Username already exists!");
				modelAndView.setViewName("signup");
			}catch(UsernameNotFoundException e){
				if (Objects.equals(user.getPassword(), user.getPasswordConfirm())){
					System.out.println("Passwords matches!");
					userService.addUser(user);
					modelAndView.setViewName("redirect:/login");
				}else{
					System.out.println("Passwords does not match!");
					modelAndView.getModel().put("message", "Passwords does not match!");
					modelAndView.setViewName("signup");
				}
			}
		}
		return modelAndView;
	}
	@RequestMapping(value="/signup",method = RequestMethod.GET)
	public String signUp(){
		return "signup";
	}
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String logIn(){
		return "login";
	}
	@RequestMapping(value="/forbidden")
	public String accessDenied(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().contains(new Authority("ROLE_ANONYMOUS"))){
			return "redirect:/login";
		}else{
			return "redirect:/";
		}
	}
}
