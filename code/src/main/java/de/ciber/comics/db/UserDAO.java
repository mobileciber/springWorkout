package de.ciber.comics.db;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.ciber.comics.db.entity.User;

@Component
public class UserDAO {
	@Autowired
	UserRepository repository;
	
	public Collection<User> findAll() {
		return repository.findAll();
	}
}
