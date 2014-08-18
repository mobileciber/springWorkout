package de.ciber.comics.db;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import de.ciber.comics.db.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Collection<User> findAll();
}
