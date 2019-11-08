package com.rs.fer.boot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rs.fer.boot.bean.User;
public interface UserRepository extends CrudRepository<User, Integer>
{

	List<User> findByUsernameAndPassword(String username, String password);
	List<User> findByIdAndPassword(int id, String newPassword);

}
