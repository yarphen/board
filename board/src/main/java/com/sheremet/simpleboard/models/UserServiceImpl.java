package com.sheremet.simpleboard.models;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private SessionFactory sessionFactory;

	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	public List<User> getAllUsers() {
		return (List<User>)currentSession().createCriteria(User.class).list();
	}

	public void addUser(User user) {
		currentSession().save(user);
	}

	public void deleteUserByName(String name) {
		currentSession().delete(getUserByName(name));
	}
	public User getUserByName(String name) throws UsernameNotFoundException  {
		List users = currentSession().createCriteria(User.class).add(Restrictions.eq("username", name)).list();
		if (users.isEmpty()){
			System.out.println("Not found user '"+name+"'");
			throw new UsernameNotFoundException(name);
		}
		return (User) users.get(0);
	}
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		return getUserByName(name);
	}

}
